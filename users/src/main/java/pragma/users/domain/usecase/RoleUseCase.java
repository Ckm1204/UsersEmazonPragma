package pragma.users.domain.usecase;

import pragma.users.domain.api.IRoleSerivePort;
import pragma.users.domain.spi.IRolePersistencePort;

public class RoleUseCase implements IRoleSerivePort {

    private final IRolePersistencePort rolePersistencePort;

    public RoleUseCase(IRolePersistencePort rolePersistencePort) {
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Integer getRoleId(String roleName) {
        // TODO Auto-generated method stub
        return 0;
    }
}
