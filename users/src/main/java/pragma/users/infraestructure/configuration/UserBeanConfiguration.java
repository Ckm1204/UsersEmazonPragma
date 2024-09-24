package pragma.users.infraestructure.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pragma.users.domain.api.IUserServicePort;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.domain.usecase.UserUseCase;
import pragma.users.infraestructure.out.jpa.adapter.UserJpaAdapter;
import pragma.users.infraestructure.out.jpa.mapper.UserMapperJPA;
import pragma.users.infraestructure.out.jpa.repository.IUserRepository;

@Configuration
public class UserBeanConfiguration {


    private final IUserRepository userRepository;
    private final UserMapperJPA userEntityMapper;

    public UserBeanConfiguration(IUserRepository userRepository, UserMapperJPA userEntityMapper) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }
}
