package com.chatapp.dto.response;

import lombok.Builder;

@Builder
public record LoginResponseDTO(String token, boolean isAuthenticated) {

}
