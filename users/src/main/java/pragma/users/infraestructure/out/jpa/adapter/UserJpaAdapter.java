package pragma.users.infraestructure.out.jpa.adapter;

import org.springframework.context.annotation.Bean;
import pragma.users.domain.model.request.UserModelRequest;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.infraestructure.out.jpa.entity.User;
import pragma.users.infraestructure.out.jpa.mapper.UserMapperJPA;
import pragma.users.infraestructure.out.jpa.repository.IUserRepository;

import java.util.Optional;


public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository userRepository;
    private final UserMapperJPA userEntityMapper;


    public UserJpaAdapter(IUserRepository userRepository, UserMapperJPA userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void createUser(UserModelRequest userModelRequest) {
        userRepository.save(userEntityMapper.toUser(userModelRequest));
    }

    @Override
    public Boolean existsUserByEmail(String email) {
        return null;
    }

    @Override
    public Boolean existsUserByPhoneNumber(String phoneNumber) {
        return null;
    }
}
