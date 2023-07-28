package com.mymusic.firebaseauthserver.dto;

import lombok.Data;

@Data
public class ValidateTokenResponseDTO {

    private String uid;
    private String email;
    private String name;

    private String token;

    private boolean validate;

    public ValidateTokenResponseDTO(String uid, String email, String name, String token, boolean validate) {
        this.uid = uid;
        this.email = email;
        this.name = name;
        this.token = token;
        this.validate = validate;
    }
}
