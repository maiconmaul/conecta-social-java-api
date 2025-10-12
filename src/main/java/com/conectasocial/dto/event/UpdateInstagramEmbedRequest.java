package com.conectasocial.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateInstagramEmbedRequest {
    
    @NotBlank(message = "Embed do Instagram é obrigatório")
    @Size(max = 5000, message = "Embed do Instagram deve ter no máximo 5000 caracteres")
    private String embeddedInstagram;
    
    // Constructors
    public UpdateInstagramEmbedRequest() {}
    
    public UpdateInstagramEmbedRequest(String embeddedInstagram) {
        this.embeddedInstagram = embeddedInstagram;
    }
    
    // Getters and Setters
    public String getEmbeddedInstagram() {
        return embeddedInstagram;
    }
    
    public void setEmbeddedInstagram(String embeddedInstagram) {
        this.embeddedInstagram = embeddedInstagram;
    }
}
