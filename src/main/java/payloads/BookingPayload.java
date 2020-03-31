package payloads;

//  {
//        "firstname": "Jim",
//        "lastname": "Brown",
//        "totalprice": 111,
//        "depositpaid": true,
//        "bookingdates": {
//              "checkin": "2018-01-01",
//              "checkout": "2019-01-01"
//         },
//        "additionalneeds": "Breakfast"
//    }

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingPayload {

    @JsonProperty
    private String firstname;
    @JsonProperty
    private String lastname;
    @JsonProperty
    private int totalprice;
    @JsonProperty
    private Boolean depositpaid;
    @JsonProperty
    private BookingDatesPayload bookingdates;
    @JsonProperty
    private String additionalneeds;


    public BookingPayload(String firstname, String lastname, int totalprice, Boolean depositpaid, BookingDatesPayload bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }
}
