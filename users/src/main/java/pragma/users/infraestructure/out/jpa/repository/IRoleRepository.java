package pragma.users.infraestructure.out.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pragma.users.infraestructure.out.jpa.entity.Role;

import java.util.Optional;


public interface IRoleRepository extends JpaRepository<Role, Integer> {

    //Method to search by name
    Optional<Role> findByName(String name);
}
