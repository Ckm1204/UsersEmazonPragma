package pragma.users.domain.spi;

import pragma.users.domain.model.RoleModel;

public interface IRolePersistencePort {

    Integer getRoleId (String roleName);
    RoleModel getRoleName (Integer roleId);

}
