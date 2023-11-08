package com.demoqa.models.account;

import lombok.Data;

@Data
public class AuthorisationRequestModel {
    private String userName,
            password;
}
