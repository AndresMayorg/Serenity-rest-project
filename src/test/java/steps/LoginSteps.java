package steps;

import api.endpoints.AuthAPI;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps; // Usa net.thucydides.core.annotations.Steps si usas versiones anteriores
import org.junit.Assert;
import utils.ExcelDataHandler;
import java.io.IOException;
import java.util.Map;

public class LoginSteps {

    @Steps
    private AuthAPI authAPI;

    private final ExcelDataHandler excelHandler = new ExcelDataHandler();

    private ValidatableResponse response;
    private String sessionCookie;

    @Given("I attempt to authenticate using user data from row {int} of feature {string}")
    public void authenticateWithUserData(int excelRow, String feature) throws IOException {
        Map<String, String> loginData = excelHandler.readExcelRowData(feature, excelRow);
        response = authAPI.performLogin(loginData);
        sessionCookie = response.extract().cookie("orangehrm");
        excelHandler.writeLog("Cookie obtenida: " + sessionCookie);
    }

    @Then("the login response should be successful")
    public void validateSuccessfulLogin() {
        int statusCode = response.extract().statusCode();
        Assert.assertEquals("El código de estado debe ser 302", 302, statusCode);
        excelHandler.writeLog("Código de estado verificado: " + statusCode);
    }

    @And("the session cookie must be generated")
    public void validateSessionCookie() {
        Assert.assertNotNull("La cookie de sesión no debe ser nula", sessionCookie);
        excelHandler.writeLog("Cookie verificada correctamente.");
    }

    @And("the response should contain the expected redirect")
    public void validateRedirectHeader() {
        String locationHeader = response.extract().header("Location");
        Assert.assertNotNull("El header Location debe existir", locationHeader);
        Assert.assertTrue("Debe contener la ruta de login o dashboard", locationHeader.contains("/auth/login"));
        excelHandler.writeLog("Redirect verificado: " + locationHeader);
    }

    @And("the response body must have the correct HTML structure")
    public void validateHtmlStructure() {
        String body = response.extract().body().asString();
        Assert.assertTrue("El body debe contener la etiqueta <html>", body.contains("<html>"));
        excelHandler.writeLog("Estructura HTML verificada correctamente.");
    }
}