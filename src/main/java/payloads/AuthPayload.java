package payloads;

//{
//        "username" : "admin",
//        "password" : "password123"
//        }

import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthPayload {

    @JsonProperty
    private String username;
    @JsonProperty
    private String password;

    /**
     * A constructor for the AuthPayload class.
     * @param username "admin"
     * @param password "password123"
     */
    public AuthPayload(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
