package com.demoqa.models.account;

import lombok.Data;

@Data
public class AuthorisationResponseModel {
    private String userId,
            username,
            password,
            token,
            expires,
            created_date,
            isActive;
}