package pragma.users.domain.usecase;

import lombok.AllArgsConstructor;
import pragma.users.domain.api.IAuthServicePort;
import pragma.users.domain.spi.IAuthPersistencePort;


public class AuthUseCase implements IAuthServicePort {

    private final IAuthPersistencePort authPersistencePort;

    public AuthUseCase(IAuthPersistencePort authPersistencePort) {
        this.authPersistencePort = authPersistencePort;
    }

    @Override
    public String login(String name, String password) {
        //TODO: Implement this method
        return "";
    }
}
