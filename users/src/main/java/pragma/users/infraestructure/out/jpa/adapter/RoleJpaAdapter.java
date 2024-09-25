package pragma.users.infraestructure.out.jpa.adapter;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pragma.users.domain.model.RoleModel;
import pragma.users.domain.spi.IRolePersistencePort;
import pragma.users.infraestructure.out.jpa.entity.Role;
import pragma.users.infraestructure.out.jpa.mapper.RoleMapperJpa;
import pragma.users.infraestructure.out.jpa.repository.IRoleRepository;



@Transactional
public class RoleJpaAdapter implements IRolePersistencePort {

    private final IRoleRepository roleRepository;
    private final RoleMapperJpa roleEntityMapper;

    public RoleJpaAdapter(IRoleRepository roleRepository, RoleMapperJpa roleEntityMapper) {
        this.roleRepository = roleRepository;
        this.roleEntityMapper = roleEntityMapper;
    }


    @Override
    public Integer getRoleId(String roleName) {
        Role roleEntity = roleRepository.findByName(roleName);
        return roleEntity.getId();
    }

    @Override
    public RoleModel getRoleName(Integer roleId) {
        Role roleEntity = roleRepository.findById(roleId).orElseThrow();

        return roleEntityMapper.toRoleModel(roleEntity);
    }
}
