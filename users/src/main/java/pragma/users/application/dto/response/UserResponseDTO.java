package pragma.users.application.dto.response;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pragma.users.infraestructure.out.jpa.entity.Role;

import java.util.Collection;
import java.util.List;

public class UserResponseDTO implements UserDetails {
    private String id;
    private String email;
    private String password;
    private Role role;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
