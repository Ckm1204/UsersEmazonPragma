package pragma.users.infraestructure.out.jpa.adapter;


import org.springframework.transaction.annotation.Transactional;
import pragma.users.domain.model.UserModel;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.infraestructure.out.jpa.mapper.UserMapperJPA;
import pragma.users.infraestructure.out.jpa.entity.User;
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
    public Optional<UserModel> findByIdentityDocument(String identityDocument) {
        Optional<User>  user = userRepository.findByIdentityDocument(identityDocument);
        return userEntityMapper.toUserModelOptional(user) ;
    }


    @Override
    public Optional<UserModel> findByPhoneNumber(String phoneNumber) {
        Optional<User> user = userRepository.findByPhoneNumber(phoneNumber);
        return userEntityMapper.toUserModelOptional(user);
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
