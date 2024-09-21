package org.example.dto;

import lombok.Builder;

@Builder
public record UserDTO (
        String name,
        String job
){}