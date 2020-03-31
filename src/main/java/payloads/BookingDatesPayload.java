package payloads;

//       "bookingdates": {
//              "checkin": "2018-01-01",
//              "checkout": "2019-01-01"
//         },

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDatesPayload {

    @JsonProperty
    private Date checkin;
    @JsonProperty
    private Date checkout;

    /**
     * A constructor for the BookingDatesPayload class.
     * @param checkin Date required for check in
     * @param checkout Date required for check out.
     */
    public BookingDatesPayload(Date checkin, Date checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }
}
