package pragma.users.application.dto.request;

import lombok.Data;

@Data
public class AuthenticationLogin {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}