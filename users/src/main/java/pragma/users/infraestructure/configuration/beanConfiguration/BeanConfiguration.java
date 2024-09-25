package pragma.users.infraestructure.configuration.beanConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pragma.users.domain.api.IAuthServicePort;
import pragma.users.domain.api.IEncryptionServicePort;
import pragma.users.domain.api.IRoleSerivePort;
import pragma.users.domain.api.IUserServicePort;
import pragma.users.domain.spi.IAuthPersistencePort;
import pragma.users.domain.spi.IRolePersistencePort;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.domain.usecase.AuthUseCase;
import pragma.users.domain.usecase.RoleUseCase;
import pragma.users.domain.usecase.UserUseCase;
import pragma.users.infraestructure.EncryptionService;
import pragma.users.infraestructure.configuration.jwtUtil.JwtService;
import pragma.users.infraestructure.out.jpa.adapter.AuthAdapter;
import pragma.users.infraestructure.out.jpa.adapter.RoleJpaAdapter;
import pragma.users.infraestructure.out.jpa.adapter.UserJpaAdapter;
import pragma.users.infraestructure.out.jpa.mapper.RoleMapperJpa;
import pragma.users.infraestructure.out.jpa.mapper.UserMapperJPA;
import pragma.users.infraestructure.out.jpa.repository.IRoleRepository;
import pragma.users.infraestructure.out.jpa.repository.IUserRepository;

@Configuration
public class BeanConfiguration {

    private final IUserRepository userRepository;
    private final UserMapperJPA userEntityMapper;
    private final IRoleRepository roleRepository;
    private final RoleMapperJpa roleEntityMapper;
    private final JwtService jwtService;


    public BeanConfiguration(IUserRepository userRepository, UserMapperJPA userEntityMapper, IRoleRepository roleRepository, RoleMapperJpa roleEntityMapper, JwtService jwtService) {
        this.userRepository = userRepository;
        this.userEntityMapper = userEntityMapper;
        this.roleRepository = roleRepository;
        this.roleEntityMapper = roleEntityMapper;
        this.jwtService = jwtService;
    }

    // Exponer AuthenticationManager como un Bean
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IEncryptionServicePort encryptionServicePort() {
        return new EncryptionService(new BCryptPasswordEncoder());
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), encryptionServicePort(), rolPersistencePort());
    }

    @Bean
    public IRolePersistencePort rolPersistencePort() {
        return new RoleJpaAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public IRoleSerivePort roleServicePort() {
        return new RoleUseCase(rolPersistencePort());
    }

    @Bean
    public IAuthPersistencePort authPersistencePort() {
        try {
            return new AuthAdapter(authenticationManager(null), jwtService, rolPersistencePort());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public IAuthServicePort authServicePort() {
        return new AuthUseCase(authPersistencePort());
    }
}
