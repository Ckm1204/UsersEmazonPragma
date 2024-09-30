package pragma.users.domain.api;


import pragma.users.domain.model.UserModel;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.util.Optional;

public interface IUserServicePort {
        Optional<User> findByEmail(String email);
        void saveUser(UserModel userModel);
}
