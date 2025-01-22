package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import task.SongsDetectRequests;
import utils.FileUtils;
import java.io.IOException;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.emptyString;
import static org.hamcrest.Matchers.is;

public class SongsDetectSteps {

    private Response response;
    private String timeZone;


    @Given("That I have a time zone (.*)$")
    public void that_i_have_a_time_zone_america_2f_chicago(String timeZone) {
        this.timeZone = timeZone;
    }
    @When("When I make a POST request to Shazam endpoint of a song encoded in base64")
    public void when_i_make_a_post_request_to_shazam_endpoint_of_a_song_encoded_in_base64() throws IOException {
        String base64Content = FileUtils.readBase64FromFile("/data/SongsDetectRequests/clinteastwood_portion_mono.txt");
        SongsDetectRequests request = SongsDetectRequests.withBody(base64Content);
        response = request.performRequest(timeZone);
    }

    @Then("The response then contains a status code {int}")
    public void the_response_then_contains_a_status_code(int statusCode) {
        response.then().statusCode(statusCode);
    }


    @Then("The response should contain at least one match")
    public void validateAtLeastOneMatch() {
        String matches = response.jsonPath().getString("matches[0].id");
        assertThat("The song id is null", matches, is(notNullValue()));
        assertThat("The song id is empty", matches, is(not(emptyString())));
    }

    @Then("Each match should have id")
    public void validateMatchFields() {
        String responseTrackUrl = response.jsonPath().getString("track.url");
        assertThat("The song url is null", responseTrackUrl, is(notNullValue()));
        assertThat("The song url is empty", responseTrackUrl, is(not(emptyString())));
    }

    @When("When I make a POST request to the Shazam endpoint for an empty song")
    public void when_i_make_a_post_request_to_the_shazam_endpoint_for_an_empty_song() throws IOException {
        String base64Content = FileUtils.readBase64FromFile("/data/SongsDetectRequests/empty.txt");
        SongsDetectRequests request = SongsDetectRequests.withBody(base64Content);
        response = request.performRequest(timeZone);
    }
}
