package com.cibertec.t1grupo1.dto;

import java.util.List;

public record LoginResponseDTO(
        String token,
        String tipo,
        long expiresIn,
        String username,
        List<String> roles
) {
}