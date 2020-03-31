package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.BookingPayload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class BookingApi extends BaseApi {

    private static final String apiUrl = baseUrl + "booking/";

    public static Response getBookings() {
        return given().get(apiUrl);
    }

    public static Response getBookingById(String bookingId, String mediaType) {
        return given()
                .header("Accept", mediaType)
                .get(apiUrl + bookingId);
    }

    public static Response postBooking(BookingPayload bookingPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(bookingPayload)
                .when()
                .post(apiUrl);
    }

    public static Response deleteBooking(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .delete(apiUrl + Integer.toString(bookingId));
    }
}
