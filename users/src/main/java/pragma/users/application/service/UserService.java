package pragma.users.application.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pragma.users.application.dto.request.UserRequestDTO;
import pragma.users.application.mapper.UserMapper;
import pragma.users.domain.api.IUserServicePort;

@Service
@Transactional
public class UserService implements IUserService {

    private final UserMapper userMapper;
    private final IUserServicePort userServicePort;
    private PasswordEncoder passwordEncoder;


    public UserService(UserMapper userMapper, IUserServicePort userServicePort, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.userServicePort = userServicePort;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUserAdmin(UserRequestDTO userRequestDTO) {
        userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        userServicePort.saveUser(userMapper.toUserModelAdmin(userRequestDTO));
    }

    @Override
    public void createUserAuxBodega(UserRequestDTO userRequestDTO) {
        userServicePort.saveUser(userMapper.toUserModelAuxBodega(userRequestDTO));
    }
}