package pragma.users.application.dto.response;

import lombok.Data;


public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse() {
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}