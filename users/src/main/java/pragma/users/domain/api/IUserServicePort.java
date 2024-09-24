package pragma.users.domain.api;


import pragma.users.domain.model.request.UserModelRequest;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.util.Optional;

public interface IUserServicePort {
        Optional<User> findByEmail(String email);
        void saveUser(UserModelRequest userModelRequest);
}
