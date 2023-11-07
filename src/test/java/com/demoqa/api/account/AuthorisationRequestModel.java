package com.demoqa.api.account;

import lombok.Data;

@Data
public class AuthorisationRequestModel {
    private String userName,
            password;
}
