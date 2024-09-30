package pragma.users.infraestructure.out.jpa.repository;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pragma.users.infraestructure.out.jpa.entity.Role;

import java.util.Optional;


public interface IRoleRepository extends JpaRepository<Role, Integer> {

    //Method to search by name
    Role findByName(String name);

    //Method to search by id
    @NotNull
    Optional<Role> findById(Integer id);
}
