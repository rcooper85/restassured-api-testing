package payloads;

//      {
//        "token":"2b459583c745685"
//       }

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthResponsePayload {

    @JsonProperty
    private String token;

    public String getToken() {
        return token;
    }
}
