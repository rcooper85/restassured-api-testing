package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AuthPayload;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {


    /**
     * String authUrl is a variable used for the URL required for the /auth endpoint.
     */
    private static final String authUrl = baseUrl + "auth/";

    /**
     *
     * @param authPayload is the object used to insert the payload that is required to send to the /auth endpoint.
     * @return this method returns a Response object from the /auth endpoint. This will be in a JSON format.
     */
    public static Response postAuth(AuthPayload authPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post(authUrl);
    }
}
