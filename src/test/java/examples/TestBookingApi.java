package examples;

import api.AuthApi;
import api.BookingApi;
import io.restassured.response.Response;
import org.junit.Test;
import payloads.AuthPayload;
import payloads.AuthResponsePayload;
import payloads.BookingDatesPayload;
import payloads.BookingPayload;

import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TestBookingApi {


    @Test
    public void getBookingsReturns200() {
        Response response = BookingApi.getBookings();
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingIdReturns200() {
        Response response = BookingApi.getBookingById("1", "application/json");
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void getBookingIdReturns418() {
        Response response = BookingApi.getBookingById("1", "text/plain");
        assertThat(response.getStatusCode(), equalTo(418));
    }

    @Test
    public void postBookingReturns200() {
        BookingDatesPayload bookingDatesPayload = new BookingDatesPayload(new Date(), new Date());
        BookingPayload bookingPayload = new BookingPayload("Stan", "Cartman", 300, false, bookingDatesPayload, "They want to leave later"  );
        Response response = BookingApi.postBooking(bookingPayload);
        assertThat(response.getStatusCode(), equalTo(200));
    }

    @Test
    public void deleteBookingReturns201() {


        // Log in and get token

        AuthPayload authPayload = new AuthPayload("admin", "password123");
        Response authResponse = AuthApi.postAuth(authPayload);
        String token = authResponse.as(AuthResponsePayload.class).getToken();

        // Use token to delete booking
        Response bookingResponse = BookingApi.deleteBooking(11, token);

        assertThat(bookingResponse.getStatusCode(), equalTo(201));


    }
}
