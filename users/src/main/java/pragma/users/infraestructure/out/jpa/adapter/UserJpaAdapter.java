package pragma.users.infraestructure.out.jpa.adapter;


import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import pragma.users.domain.model.UserModel;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.infraestructure.out.jpa.mapper.UserMapperJPA;
import pragma.users.infraestructure.out.jpa.entity.User;
import pragma.users.infraestructure.out.jpa.repository.IRoleRepository;
import pragma.users.infraestructure.out.jpa.repository.IUserRepository;

import java.util.Optional;


@Transactional
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserMapperJPA userEntityMapper;

    public UserJpaAdapter(IUserRepository userRepository, UserMapperJPA userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void createUser(UserModel userModel) {
        User user = userEntityMapper.toUser(userModel);
        userRepository.save(user);

    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Boolean existsUserByPhoneNumber(String phoneNumber) {
        return null;
    }


//    @Override
//    public void saveWareHouseAssistantUser(User user) {
//
//        UserEntity userEntity = userEntityMapper.toUserEntity(user);
//
//        userRepository.save(userEntity);
//    }
//
//
//    @Override
//    public void saveClientUser(User user) {
//        UserEntity userEntity = userEntityMapper.toUserEntity(user);
//
//        userRepository.save(userEntity);
//    }
}
