package pragma.users.application.service;

import pragma.users.application.dto.request.AuthenticationLogin;
import pragma.users.application.dto.response.AuthenticationResponse;

public interface IAuthService {

    AuthenticationResponse login(AuthenticationLogin request);

}
