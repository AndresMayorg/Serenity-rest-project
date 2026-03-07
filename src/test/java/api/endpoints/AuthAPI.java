package api.endpoints;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import utils.ApiConfig;
import net.serenitybdd.annotations.Steps; // IMPORTANTE
import java.util.Map;

public class AuthAPI {

    @Steps
    private ApiConfig apiConfig;

    public ValidatableResponse performLogin(Map<String, String> loginData) {
        return SerenityRest.given()
                .baseUri(apiConfig.getBaseUrl())
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", loginData.get("username"))
                .formParam("password", loginData.get("password"))
                .log().all()
                .when()
                .post(ApiConfig.LOGIN_ENDPOINT)
                .then()
                .log().ifValidationFails();
    }
}