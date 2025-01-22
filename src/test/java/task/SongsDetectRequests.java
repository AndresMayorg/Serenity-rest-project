package task;

import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import utils.ApiConfig;
import java.util.Arrays;
import java.util.List;


public class SongsDetectRequests {
    private final String requestBody;

    public SongsDetectRequests(String requestBody) {
        this.requestBody = requestBody;
    }

    public static SongsDetectRequests withBody(String requestBody) {
        return new SongsDetectRequests(requestBody);
    }

    public Response performRequest(String timeZone) {
        String endpoint = ApiConfig.BASE_URL + "/songs/v2/detect";

        List<Header> headersList = Arrays.asList(
                new Header("Content-Type", ApiConfig.CONTENT_TYPE),
                new Header("x-rapidapi-host", ApiConfig.API_HOST),
                new Header("x-rapidapi-key", ApiConfig.API_KEY)
        );

        Headers headers = new Headers(headersList);

        return SerenityRest.given()
                .headers(headers)
                .contentType("text/plain")
                .body(requestBody)
                .when()
                .post(endpoint);
    }
}
