package pragma.users.domain.usecase;

import lombok.AllArgsConstructor;
import pragma.users.domain.api.IAuthServicePort;
import pragma.users.domain.model.UserModel;
import pragma.users.domain.spi.IAuthPersistencePort;
import pragma.users.domain.usecase.validateConstants.UserValidationConstants;
import pragma.users.infraestructure.exceptions.AuthValidationException;


public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String email, String password) {

        if(!authPersistencePort.validateCredentials(email, password)){
            throw new AuthValidationException(UserValidationConstants.INVALID_CREDENTIALS_MESSAGE);
        }
        UserModel user  = authPersistencePort.authenticate(email, password);
        return authPersistencePort.generateToken(user);

    }
}
