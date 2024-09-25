package pragma.users.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pragma.users.domain.model.UserModel;
import pragma.users.infraestructure.out.jpa.entity.Role;
import pragma.users.infraestructure.out.jpa.entity.User;



@Mapper(componentModel = "spring")
public interface UserMapperJPA {

    @Mapping(target = "role.id", source = "role")
    User toUser(UserModel userModel);

    default Role map(Integer roleId) {
        Role role = new Role();
        role.setId(roleId);
        return role;
    }
}
