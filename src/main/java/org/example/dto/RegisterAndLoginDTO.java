package org.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterAndLoginDTO {

    private String email;
    private String password;
}
