package pragma.users.domain.spi;

import pragma.users.domain.model.UserModel;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.util.Optional;

public interface IUserPersistencePort {

    Optional<User> findByEmail(String email);
    void createUser(UserModel userModel);
    Optional<UserModel> findByIdentityDocument(String identityDocument);
    Optional<UserModel> findByPhoneNumber(String phoneNumber);



}
