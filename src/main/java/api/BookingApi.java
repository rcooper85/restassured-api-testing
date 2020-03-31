package api;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.BookingPayload;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;


public class BookingApi extends BaseApi {

    /**
     * String apiUrl is the URL required for the /booking endpoint.
     */
    private static final String apiUrl = baseUrl + "booking/";

    /**
     * A method that performs a GET request against the /booking endpoint.
     * @return This method returns a Response object from the /booking endpoint. This Response will be in a JSON format.
     */
    public static Response getBookings() {
        return given().get(apiUrl);
    }

    /**
     * A method that performs a GET request to retrieve the information associated with a booking ID.
     * @param bookingId The ID of the booking request you're trying to retrieve.
     * @param mediaType The content type required. E.G application/json.
     * @return Returns a Response object for the specified booking ID in JSON format.
     */
    public static Response getBookingById(String bookingId, String mediaType) {
        return given()
                .header("Accept", mediaType)
                .get(apiUrl + bookingId);
    }

    /**
     * A method that performs a POST request that creates a new booking.
     * @param bookingPayload is a POJO that uses the @JsonProperty function to inject JSON data.
     * @return a Response object is returned in JSON format.
     */
    public static Response postBooking(BookingPayload bookingPayload) {
        return given()
                .contentType(ContentType.JSON)
                .body(bookingPayload)
                .when()
                .post(apiUrl);
    }

    /**
     * A method that performs a DELETE request for a specific booking ID.
     * @param bookingId the booking ID that you wish to delete.
     * @param token this is the AUTH token that is required to authenticate with the restful booker API.
     * @return A Response object is returned in JSON format.
     */
    public static Response deleteBooking(int bookingId, String token) {
        return given()
                .header("Cookie", "token=" + token)
                .delete(apiUrl + Integer.toString(bookingId));
    }
}
