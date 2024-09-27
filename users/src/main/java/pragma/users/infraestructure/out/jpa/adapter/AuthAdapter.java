package pragma.users.infraestructure.out.jpa.adapter;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import pragma.users.domain.model.RoleModel;
import pragma.users.domain.model.UserModel;
import pragma.users.domain.spi.IAuthPersistencePort;
import pragma.users.domain.spi.IRolePersistencePort;
import pragma.users.infraestructure.configuration.jwtConfiguration.AuthConstants;
import pragma.users.infraestructure.configuration.jwtConfiguration.JwtService;
import pragma.users.infraestructure.out.jpa.entity.CustomUserDetails;

import java.util.HashMap;
import java.util.Map;


public class AuthAdapter implements IAuthPersistencePort {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IRolePersistencePort rolePersistencePort;

    public AuthAdapter(AuthenticationManager authenticationManager, JwtService jwtService, IRolePersistencePort rolePersistencePort) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public UserModel authenticate(String email, String password) {
        Authentication authUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        CustomUserDetails customUserDetails = (CustomUserDetails) authUser.getPrincipal();

        UserModel user = new UserModel();
        user.setId(customUserDetails.getUserEntity().getId());
        user.setEmail(customUserDetails.getUsername());
        user.setRole(customUserDetails.getUserEntity().getRole().getId());

        return user;
    }

    @Override
    public boolean validateCredentials(String email, String password) {

        try {
             Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            return authentication.isAuthenticated();
        } catch (BadCredentialsException e) {
            return false;
        }


    }

    @Override
    public String generateToken(UserModel user) {

        return jwtService.generateToken(user, generateExtraClaims(user));
    }

    private Map<String, Object> generateExtraClaims(UserModel user) {
        Map<String, Object> extraClaims = new HashMap<>();
        RoleModel role = rolePersistencePort.getRoleName(user.getRole());
        extraClaims.put(AuthConstants.AUTHORITIES_KEY, AuthConstants.ROLE_PREFIX+role.getName());


        return extraClaims;
    }
}
