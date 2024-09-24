package pragma.users.infraestructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<User, Integer> {

    // buscar por nombre
    Optional<User> findByEmail(String email);
    // method para verificar si existe un usuario con el email
    Boolean existsUserByEmail (String email);
    // method para verificar si el numero de telefono ya esta registrado
    Boolean existsUserByPhoneNumber (String phoneNumber);

}
