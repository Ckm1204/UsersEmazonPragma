package pragma.users.application.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pragma.users.application.dto.request.AuthenticationLogin;
import pragma.users.application.dto.response.AuthenticationResponse;
import pragma.users.domain.api.IAuthServicePort;

@Service
public class AuthService implements IAuthService {

    private final IAuthServicePort authServicePort;

    public AuthService(IAuthServicePort authServicePort) {
        this.authServicePort = authServicePort;
    }


    @Override
    public AuthenticationResponse login(AuthenticationLogin request) {

        String jwt = authServicePort.login(request.getEmail(), request.getPassword());

        return new AuthenticationResponse(jwt);
    }
}
