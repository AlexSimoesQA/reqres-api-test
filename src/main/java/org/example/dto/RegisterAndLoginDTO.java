package org.example.dto;

import lombok.Builder;

@Builder
public record RegisterAndLoginDTO (
        String email,
        String password
){}
