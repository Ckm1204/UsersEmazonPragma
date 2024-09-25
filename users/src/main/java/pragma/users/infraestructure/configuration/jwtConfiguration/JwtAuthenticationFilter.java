package pragma.users.infraestructure.configuration.jwtConfiguration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import pragma.users.infraestructure.configuration.CustomUserDetailsService;
import pragma.users.infraestructure.configuration.jwtUtil.JwtService;
import pragma.users.infraestructure.out.jpa.entity.CustomUserDetails;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, CustomUserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String jwt = authHeader.split(" ")[1];

        try{
            if(!jwtService.isValid(jwt)){
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Invalid token");
                return;
            }

            String username = jwtService.extractUsername(jwt);

            CustomUserDetails userDetails = (CustomUserDetails) userDetailsService.loadUserById(Integer.parseInt(username));

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, null, userDetails.getAuthorities());


            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
        catch(Exception e){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
            return;
        }

        filterChain.doFilter(request, response);


    }
}
