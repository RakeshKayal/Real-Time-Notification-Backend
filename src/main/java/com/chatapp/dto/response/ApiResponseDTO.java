package com.chatapp.dto.response;

import lombok.Builder;

@Builder
public record ApiResponseDTO(String message, boolean status) {
}
