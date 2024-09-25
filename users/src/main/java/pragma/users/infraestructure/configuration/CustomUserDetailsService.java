package pragma.users.infraestructure.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pragma.users.infraestructure.out.jpa.entity.CustomUserDetails;
import pragma.users.infraestructure.out.jpa.repository.IUserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final IUserRepository userRepository;

    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // TODO: Implement this method
    }


    public UserDetails loadUserById(Integer id) {
        return userRepository.findById(id).map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }
}