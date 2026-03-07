package utils;

import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;

public class ApiConfig {

    private EnvironmentVariables environmentVariables;

    public String getBaseUrl() {
        return EnvironmentSpecificConfiguration.from(environmentVariables)
                .getProperty("base.url");
    }

    public static final String LOGIN_ENDPOINT = "/web/index.php/auth/validate";
}