package api.endpoints;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.model.util.EnvironmentVariables;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthAPI {

    private EnvironmentVariables environmentVariables;

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64)";
    private static final String LOGIN_ENDPOINT = "/web/index.php/auth/validate";
    private static final String LOGIN_PAGE = "/web/index.php/auth/login";

    public ValidatableResponse performLogin(Map<String, String> loginData) {

        String baseUrl = EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("webdriver.base.url");

        io.restassured.response.Response setupResponse = SerenityRest.given()
                .baseUri(baseUrl)
                .header("User-Agent", USER_AGENT)
                .get(LOGIN_PAGE);

        String sessionCookie = setupResponse.getCookie("orangehrm");
        String csrfToken = extractToken(setupResponse.getBody().asString());

        return SerenityRest.given()
                .baseUri(baseUrl)
                .header("User-Agent", USER_AGENT)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .cookie("orangehrm", sessionCookie)
                .formParams(Map.of(
                        "_token", csrfToken,
                        "username", loginData.get("username"),
                        "password", loginData.get("password")
                ))
                .when()
                .post(LOGIN_ENDPOINT)
                .then();
    }

    private String extractToken(String htmlBody) {
        Pattern pattern = Pattern.compile(":token=\"&quot;(.+?)&quot;\"");
        Matcher matcher = pattern.matcher(htmlBody);
        if (matcher.find()) return matcher.group(1);
        throw new IllegalStateException("CSRF Token no encontrado en el HTML.");
    }

}