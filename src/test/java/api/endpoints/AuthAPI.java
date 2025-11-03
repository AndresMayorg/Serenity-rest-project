package api.endpoints;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import utils.ApiConfig;
import java.util.Map;

public class AuthAPI {

    public ValidatableResponse performLogin(Map<String, String> loginData) {
        return SerenityRest.given()
                .baseUri(ApiConfig.BASE_URL)
                .contentType("application/x-www-form-urlencoded")
                .formParam("username", loginData.get("username"))
                .formParam("password", loginData.get("password"))
                .when()
                .post(ApiConfig.LOGIN_ENDPOINT)
                .then();
    }
}
