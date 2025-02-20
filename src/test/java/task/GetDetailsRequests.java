package task;

import io.restassured.http.Headers;
import io.restassured.http.Header;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import utils.ApiConfig;
import java.util.Arrays;
import java.util.List;

public class GetDetailsRequests {

    public static Response getSongDetails(String songId) {
        String endpoint = ApiConfig.BASE_URL + "/songs/v2/get-details?id=" + songId + "&l=en-US";

        List<Header> headersList = Arrays.asList(
                new Header("x-rapidapi-host", ApiConfig.API_HOST),
                new Header("x-rapidapi-key", ApiConfig.API_KEY)
        );

        Headers headers = new Headers(headersList);

        return SerenityRest.given()
                .headers(headers)
                .when()
                .get(endpoint);
    }
}
