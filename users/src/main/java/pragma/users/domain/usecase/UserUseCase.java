package pragma.users.domain.usecase;

import pragma.users.domain.api.IUserServicePort;
import pragma.users.domain.model.request.UserModelRequest;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.infraestructure.exceptionService.exceptions.*;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.regex.Pattern;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    // importaciones para email válido
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX, Pattern.CASE_INSENSITIVE);


    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

    @Override
    public void saveUser(UserModelRequest userModelRequest) {
        if (userPersistencePort.findByEmail(userModelRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExist();
        } else if (!validatePhoneNumber(userModelRequest.getPhoneNumber())) {
            throw new PhoneNumberIsNotValid();
        } else if ( userModelRequest.getPhoneNumber().length()>13 && userModelRequest.getPhoneNumber().length()<10) {
            throw new PhoneNumberLengthIsNotValid();
        } else if (userPersistencePort.existsUserByPhoneNumber(userModelRequest.getPhoneNumber())) {
            throw new PhoneNumberAlreadyIsUse();
        } else if (!validateOnlyNumber(userModelRequest.getIdentityDocument())) {
            throw new DocumentIdentityOnlyNumeric();
        } else if (!validateEmail(userModelRequest.getEmail())) {
            throw new EmailIsNotValid();
        } else if (!isAdultValidate(userModelRequest.getBirthDate())) {
            throw new UserIsNotAdult();

        } else {
            userPersistencePort.createUser(userModelRequest);
        }

    }


    public static boolean isAdultValidate(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            return false;} // Retorna false si la fecha es nula
        LocalDate hoy = LocalDate.now(); // Fecha actual
        Period edad = Period.between(fechaNacimiento, hoy); // Calcula la diferencia
        return edad.getYears() >= 18; // Retorna true si tiene 18 años o más
    }


    public static boolean validatePhoneNumber(String input) {
        // La expresión regular permite solo un + al inicio seguido de dígitos
        String regex = "^\\+[0-9]+$";
        return input.matches(regex);
    }

    public static boolean validateOnlyNumber(String input) {
        // Verifica si el String es nulo o vacío
        if (input == null || input.isEmpty()) {
            return false;}
        // Comprobar cada carácter del String
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false; // Retorna false si encuentra un carácter no numérico
            }}return true;
    }

    public static boolean validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false; // Retorna false si el String es nulo o vacío
        }
        return EMAIL_PATTERN.matcher(email).matches(); // Verifica si el email coincide con el patrón
    }
}
