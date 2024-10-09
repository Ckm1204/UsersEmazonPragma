package pragma.users.domain.spi;

import pragma.users.domain.model.UserModel;

public interface IAuthPersistencePort {

    UserModel authenticate(String email, String password);

    boolean validateCredentials(String email, String password);

    String generateToken(UserModel user);
}
