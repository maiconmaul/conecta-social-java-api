package com.conectasocial.dto.event;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateInstagramEmbedRequest {
    
    @NotBlank(message = "Embed do Instagram é obrigatório")
    @Size(max = 5000, message = "Embed do Instagram deve ter no máximo 5000 caracteres")
    private String embedded_instagram;
    
    // Constructors
    public UpdateInstagramEmbedRequest() {}
    
    public UpdateInstagramEmbedRequest(String embedded_instagram) {
        this.embedded_instagram = embedded_instagram;
    }
    
    // Getters and Setters
    public String getEmbedded_instagram() {
        return embedded_instagram;
    }
    
    public void setEmbedded_instagram(String embedded_instagram) {
        this.embedded_instagram = embedded_instagram;
    }
}

