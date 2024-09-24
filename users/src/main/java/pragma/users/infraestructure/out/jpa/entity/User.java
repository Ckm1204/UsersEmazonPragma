package pragma.users.infraestructure.out.jpa.entity;

import jakarta.persistence.*;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Data
@Entity
@Table(name = "User", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName", nullable = false, length = 50)
    private String firstName;

    @Column(name = "lastName", nullable = false, length = 50)
    private String lastName;

    @Column(name = "identityDocument", nullable = false, length = 20)
    private String identityDocument;

    @Column(name = "phoneNumber", nullable = false, length = 13)
    private String phoneNumber;

    @Column(name = "birthDate", nullable = false)
    private LocalDate birthDate;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = false)
    private Role role;

    @Column(name = "created_at", updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt;

    public User(Role role, String password, String email, LocalDate birthDate, String phoneNumber, String identityDocument, String lastName, String firstName) {
        this.role = role;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.phoneNumber = phoneNumber;
        this.identityDocument = identityDocument;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public User() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    }
