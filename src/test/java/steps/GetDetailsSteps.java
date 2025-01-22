package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import task.GetDetailsRequests;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetDetailsSteps {

    private Response response;
    private String songId;

    @Given("that I have a song ID (.*)$")
    public void thatIHaveASongID(String id) {
        this.songId = id;
    }

    @When("I make a GET request to the Shazam endpoint")
    public void i_make_a_get_request_to_the_shazam_endpoint() {
        response = GetDetailsRequests.getSongDetails(songId);
    }

    @Then("the response must have a status code of {int}")
    public void the_response_must_have_a_status_code_of(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the song title must not be empty")
    public void the_song_title_must_not_be_empty() {
        String songTitle = response.jsonPath().getString("data[0].attributes.name");
        assertThat("The song title is null", songTitle, is(notNullValue()));
        assertThat("The song title is empty", songTitle, is(not(emptyString())));
    }

    @Then("the song ID in the response must be the same as requested")
    public void the_song_id_in_the_response_must_be_the_same_as_requested() {
        String responseSongId = response.jsonPath().getString("data[0].id");
        assertThat("The song ID does not match the requested one", responseSongId, is(equalTo(songId)));
    }

    @Then("the response should contain an error with status 404")
    public void the_response_should_contain_an_error_with_status_404() {
        String status = response.jsonPath().getString("errors[0].status");
        assertThat("Status code is not 404", status, is(equalTo("404")));
    }


    @Then("the error title should be 'Resource Not Found'")
    public void the_error_title_should_be_resource_not_found() {
        String title = response.jsonPath().getString("errors[0].title");
        assertThat("The title of the error is not as expected", title, is(equalTo("Resource Not Found")));
    }


    @Then("the error code should be '40400'")
    public void the_error_code_should_be_40400() {
        String errorCode = response.jsonPath().getString("errors[0].code");
        assertThat("The error code is not 40400", errorCode, is(equalTo("40400")));
    }
}
