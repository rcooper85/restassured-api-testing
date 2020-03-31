package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.AuthPayload;

import static io.restassured.RestAssured.given;

public class AuthApi extends BaseApi {


    private static final String apiUrl = baseUrl + "auth/";

    public static Response postAuth(AuthPayload authPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(authPayload)
                .when()
                .post(apiUrl);
    }
}
