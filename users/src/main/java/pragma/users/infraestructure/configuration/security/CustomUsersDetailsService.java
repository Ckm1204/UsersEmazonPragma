package pragma.users.infraestructure.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pragma.users.infraestructure.out.jpa.entity.Role;
import pragma.users.infraestructure.out.jpa.repository.IUserRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUsersDetailsService implements UserDetailsService {
    private IUserRepository usuariosRepo;

    @Autowired
    public CustomUsersDetailsService(IUserRepository usuariosRepo) {
        this.usuariosRepo = usuariosRepo;
    }

    // Método para traernos una lista de autoridades por medio de una lista de roles
    public Collection<GrantedAuthority> mapToAuthorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    // Método para traernos un usuario con todos sus datos por medio de su email
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        pragma.users.infraestructure.out.jpa.entity.User usuarios = usuariosRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        // Obtener el rol directamente, asumiendo que solo hay uno
        Role role = usuarios.getRole(); // Aquí obtienes el rol
        Collection<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(role.getName()));
        return new User(usuarios.getUsername(), usuarios.getPassword(), authorities);
    }
}
