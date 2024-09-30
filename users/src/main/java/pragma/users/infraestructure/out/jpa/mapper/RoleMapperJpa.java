package pragma.users.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import pragma.users.domain.model.RoleModel;
import pragma.users.infraestructure.out.jpa.entity.Role;

@Mapper(componentModel = "spring", uses = {RoleMapperJpa.class})
public interface RoleMapperJpa {

    RoleModel toRoleModel(Role role);

    Role toRole(RoleModel roleModel);
}
