package pragma.users.infraestructure.configuration.jwtConfiguration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtGenerator {

    //Método para crear un token por medio de la authentication
    public String generateToken(Authentication authentication) {

        String username = authentication.getName();
        Date currenTime = new Date();
        Date expirationToken = new Date(currenTime.getTime() + constantsSecurity.JWT_EXPIRATION_TOKEN);


        //Linea para generar el token
        return Jwts.builder() //Construimos un token JWT llamado token
                .setSubject(username) //Aca establecemos el nombre de usuario que está iniciando sesión
                .setIssuedAt(new Date()) //Establecemos la fecha de emisión del token en el momento actual
                .setExpiration(expirationToken) //Establecemos la fecha de caducidad del token
                .signWith(SignatureAlgorithm.HS512, constantsSecurity.JWT_FIRMA) /*Utilizamos este método para firmar
            nuestro token y de esta manera evitar la manipulación o modificación de este*/
                .compact();//Este método finaliza la construcción del token y lo convierte en una cadena compacta
    }


    //Método para extraer un Username apartir de un token
    public String getUsernameFromJwt(String token) {
        Claims claims = Jwts.parser() // El método parser se utiliza con el fin de analizar el token
                .setSigningKey(constantsSecurity.JWT_FIRMA)// Establece la clave de firma, que se utiliza para verificar la firma del token
                .parseClaimsJws(token) //Se utiliza para verificar la firma del token, apartir del String "token"
                .getBody(); /*Obtenemos el claims(cuerpo) ya verificado del token el cual contendrá la información de
                nombre de usuario, fecha de expiración y firma del token*/
        return claims.getSubject(); //Devolvemos el nombre de usuario
    }

    //Método para validar el token
    public Boolean validateToken(String token) {
        try {
            //Validación del token por medio de la firma que contiene el String token(token)
            //Si son idénticas validara el token o caso contrario saltara la excepción de abajo
            Jwts.parser().setSigningKey(constantsSecurity.JWT_FIRMA).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Jwt ah expirado o esta incorrecto");
        }
    }
}
