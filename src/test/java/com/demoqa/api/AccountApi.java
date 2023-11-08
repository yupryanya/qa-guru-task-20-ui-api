package com.demoqa.api;

import com.demoqa.config.App;
import com.demoqa.models.account.AuthorisationRequestModel;
import com.demoqa.models.account.AuthorisationResponseModel;

import static com.demoqa.specs.AccountSpec.requestSpec;
import static com.demoqa.specs.AccountSpec.responseWithStatusCode200Spec;
import static io.restassured.RestAssured.given;

public class AccountApi {
    public static AuthorisationResponseModel getAuthorizationResponse() {
        AuthorisationRequestModel authData = new AuthorisationRequestModel();

        authData.setUserName(App.appConfig.username());
        authData.setPassword(App.appConfig.password());

        return given(requestSpec)
                .body(authData)
                .when()
                .post("/Login")
                .then()
                .spec(responseWithStatusCode200Spec)
                .extract().as(AuthorisationResponseModel.class);
    }
}