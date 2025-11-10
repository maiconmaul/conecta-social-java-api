package com.conectasocial.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Service
public class InstagramEmbedService {
    
    private static final Logger logger = LoggerFactory.getLogger(InstagramEmbedService.class);
    
    @Value("${instagram.embed.service.url:http://localhost:3008}")
    private String embedServiceUrl;
    
    private final RestTemplate restTemplate;
    
    public InstagramEmbedService() {
        this.restTemplate = new RestTemplate();
    }
    
    /**
     * Converte uma URL do Instagram em código HTML de embed
     * @param instagramUrl URL do post do Instagram
     * @return HTML do embed ou null se não conseguir obter
     */
    public String getEmbedFromUrl(String instagramUrl) {
        if (instagramUrl == null || instagramUrl.trim().isEmpty()) {
            return null;
        }
        
        // Se já é HTML (contém <blockquote ou <iframe), retorna como está
        if (isHtmlEmbed(instagramUrl)) {
            return instagramUrl;
        }
        
        // Se é uma URL do Instagram, tenta buscar o embed, mas se falhar, retorna a URL
        if (isInstagramUrl(instagramUrl)) {
            try {
                return fetchEmbedFromInstagram(instagramUrl);
            } catch (Exception e) {
                logger.warn("Não foi possível obter o embed do Instagram para URL: " + instagramUrl + ". Salvando a URL diretamente.", e);
                // Retorna a URL diretamente para que o frontend possa renderizar
                return instagramUrl;
            }
        }
        
        // Se não é URL nem HTML, retorna como está (compatibilidade)
        return instagramUrl;
    }
    
    /**
     * Verifica se a string já é um HTML de embed
     */
    private boolean isHtmlEmbed(String input) {
        if (input == null) {
            return false;
        }
        String lower = input.toLowerCase().trim();
        return lower.contains("<blockquote") || lower.contains("<iframe");
    }
    
    /**
     * Verifica se a string é uma URL do Instagram
     */
    private boolean isInstagramUrl(String url) {
        if (url == null) {
            return false;
        }
        String lower = url.toLowerCase().trim();
        return (lower.startsWith("http://") || lower.startsWith("https://")) && 
               (lower.contains("instagram.com") || lower.contains("instagr.am"));
    }
    
    /**
     * Busca o embed HTML usando o microserviço Node.js ou gera localmente
     */
    private String fetchEmbedFromInstagram(String url) {
        // Primeiro tenta usar o microserviço Node.js
        try {
            String serviceUrl = embedServiceUrl + "/generate-embeds";
            
            // Prepara a requisição
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("urls", Arrays.asList(url));
            
            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
            
            // Faz a requisição para o microserviço
            ResponseEntity<GenerateEmbedsResponse> response = restTemplate.exchange(
                serviceUrl,
                HttpMethod.POST,
                entity,
                GenerateEmbedsResponse.class
            );
            
            GenerateEmbedsResponse embedResponse = response.getBody();
            if (embedResponse != null && embedResponse.isSuccess() && 
                embedResponse.getEmbeds() != null && !embedResponse.getEmbeds().isEmpty()) {
                
                String html = embedResponse.getEmbeds().get(0);
                
                // Remove o script do Instagram se estiver presente (o frontend carrega o script na página principal)
                html = html.replaceAll("(?i)<script[^>]*instagram\\.com/embed\\.js[^>]*></script>", "");
                html = html.replaceAll("(?i)<script[^>]*instagram\\.com/embed\\.js[^>]*/>", "");
                html = html.trim();
                
                return html;
            }
            
            logger.warn("Microserviço retornou resposta vazia, gerando embed localmente");
            
        } catch (Exception e) {
            logger.warn("Erro ao conectar com microserviço de embed: " + e.getMessage() + ". Gerando embed localmente.");
        }
        
        // Se o microserviço falhar, gera o embed localmente
        return generateEmbedLocally(url);
    }
    
    /**
     * Gera o embed HTML localmente baseado na URL do Instagram
     */
    private String generateEmbedLocally(String url) {
        String postId = extractPostId(url);
        
        if (postId == null) {
            logger.warn("Não foi possível extrair POST_ID da URL: " + url);
            return url; // Retorna a URL original se não conseguir extrair o POST_ID
        }
        
        // Gera o embed HTML no formato que o frontend pode renderizar
        // Apenas o blockquote é necessário - o frontend carrega o script do Instagram na página principal
        // e chama instgrm.Embeds.process() manualmente
        String embedHtml = String.format(
            "<blockquote class=\"instagram-media\" " +
            "data-instgrm-captioned " +
            "data-instgrm-permalink=\"%s\" " +
            "data-instgrm-version=\"14\" " +
            "style=\"background:#FFF; border:0; border-radius:3px; box-shadow:0 0 1px 0 rgba(0,0,0,0.5),0 1px 10px 0 rgba(0,0,0,0.15); margin: 1px; max-width:540px; min-width:326px; padding:0; width:99.375%%; width:-webkit-calc(100%% - 2px); width:calc(100%% - 2px);\">" +
            "</blockquote>",
            url
        );
        
        return embedHtml;
    }
    
    /**
     * Extrai o POST_ID de uma URL do Instagram
     */
    private String extractPostId(String url) {
        if (url == null || url.trim().isEmpty()) {
            return null;
        }
        
        try {
            // Regex para capturar o POST_ID de URLs do Instagram
            // Suporta vários formatos:
            // - https://www.instagram.com/p/POST_ID/
            // - https://www.instagram.com/p/POST_ID
            // - https://www.instagram.com/reel/POST_ID/
            // - https://www.instagram.com/tv/POST_ID/
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(
                "https?://(www\\.)?instagram\\.com/(p|reel|tv)/([A-Za-z0-9_-]+)"
            );
            java.util.regex.Matcher matcher = pattern.matcher(url);
            
            if (matcher.find() && matcher.groupCount() >= 3) {
                return matcher.group(3);
            }
            
            return null;
        } catch (Exception e) {
            logger.error("Erro ao extrair POST_ID da URL: " + url, e);
            return null;
        }
    }
    
    /**
     * Classe para deserializar a resposta do microserviço de embed
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GenerateEmbedsResponse {
        @JsonProperty("success")
        private boolean success;
        
        @JsonProperty("embeds")
        private List<String> embeds;
        
        @JsonProperty("total")
        private Integer total;
        
        @JsonProperty("processed")
        private Integer processed;
        
        // Getters and Setters
        public boolean isSuccess() {
            return success;
        }
        
        public void setSuccess(boolean success) {
            this.success = success;
        }
        
        public List<String> getEmbeds() {
            return embeds;
        }
        
        public void setEmbeds(List<String> embeds) {
            this.embeds = embeds;
        }
        
        public Integer getTotal() {
            return total;
        }
        
        public void setTotal(Integer total) {
            this.total = total;
        }
        
        public Integer getProcessed() {
            return processed;
        }
        
        public void setProcessed(Integer processed) {
            this.processed = processed;
        }
    }
}

