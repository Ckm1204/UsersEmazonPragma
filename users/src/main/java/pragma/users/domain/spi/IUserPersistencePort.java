package pragma.users.domain.spi;

import pragma.users.application.dto.response.UserResponseDTO;
import pragma.users.domain.model.request.UserModelRequest;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.util.Optional;

public interface IUserPersistencePort {

    Optional<User> findByEmail(String email);
    void createUser(UserModelRequest userModelRequest);

    Boolean existsUserByEmail (String email);
    // method para verificar si el numero de telefono ya esta registrado
    Boolean existsUserByPhoneNumber (String phoneNumber);


}
