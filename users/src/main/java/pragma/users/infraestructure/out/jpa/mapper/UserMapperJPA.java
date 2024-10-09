package pragma.users.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pragma.users.domain.model.UserModel;
import pragma.users.infraestructure.out.jpa.entity.Role;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.util.Optional;


@Mapper(componentModel = "spring")
public interface UserMapperJPA {

    @Mapping(target = "role.id", source = "role")
    User toUser(UserModel userModel);

    default Role map(Integer roleId) {
        Role role = new Role();
        role.setId(roleId);
        return role;
    }

    @Mapping(target = "role", source = "role.id")
    UserModel toUserModel(User user);

    @Mapping(target = "role", source = "role.id")
    default Optional<UserModel> toUserModelOptional(Optional<User> user) {
        return user.map(this::toUserModel);
    }
}
