package org.example.dataFactory;

import org.example.dto.RegisterAndLoginDTO;
import org.example.dto.UserDTO;

public class ReqresDataFactory {

    public UserDTO setUser(String name, String job) {
        return UserDTO.builder()
                .name(name)
                .job(job)
                .build();
    }

    public RegisterAndLoginDTO setRegister(String email, String password) {
        return RegisterAndLoginDTO.builder()
                .email(email)
                .password(password)
                .build();
    }
}
