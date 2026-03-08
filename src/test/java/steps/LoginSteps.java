package steps;

import api.endpoints.AuthAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import org.hamcrest.Matchers;
import utils.ExcelDataHandler;
import java.io.IOException;
import java.util.Map;

public class LoginSteps {

    @Steps
    private AuthAPI authAPI;

    private final ExcelDataHandler excelHandler = new ExcelDataHandler();
    private ValidatableResponse response;

    @Given("User attempts to authenticate using data from row {int} of {string}")
    public void authenticateWithUserData(int excelRow, String feature) throws IOException {
        Map<String, String> loginData = excelHandler.readExcelRowData(feature, excelRow);
        response = authAPI.performLogin(loginData);
    }

    @Then("he should receive a redirection status")
    public void validateSuccessfulLogin() {
        response.statusCode(302);
        excelHandler.writeLog("Autenticación procesada: Status 302 OK.");
    }

    @And("a new session cookie should be assigned")
    public void validateSessionCookiePersistence() {
        response.header("Set-Cookie", Matchers.notNullValue());
        response.cookie("orangehrm", Matchers.not(Matchers.emptyString()));
        excelHandler.writeLog("Persistencia de sesión confirmada: Cookie 'orangehrm' generada.");
    }

    @And("he should be granted access to the dashboard")
    public void validateRedirectToHome() {
        response.header("Location", Matchers.containsString("/dashboard/index"));
        excelHandler.writeLog("Redirección al Dashboard confirmada.");
    }

    @And("the response must be valid HTML")
    public void validateHtmlStructure() {
        response.body(Matchers.containsString("<html>"));
    }
}