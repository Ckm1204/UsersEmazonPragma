package pragma.users.domain.usecase;

import pragma.users.domain.api.IEncryptionServicePort;
import pragma.users.domain.api.IUserServicePort;
import pragma.users.domain.model.UserModel;
import pragma.users.domain.spi.IRolePersistencePort;
import pragma.users.domain.spi.IUserPersistencePort;
import pragma.users.domain.usecase.validateConstants.CreateUserValidationConstants;
import pragma.users.infraestructure.exceptions.AgeValidationException;
import pragma.users.infraestructure.exceptions.EmailValidationException;
import pragma.users.infraestructure.exceptions.PhoneNumberValidation;
import pragma.users.infraestructure.out.jpa.entity.User;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;
    private final IEncryptionServicePort encryptionServicePort;
    private final IRolePersistencePort rolePersistencePort;

    // importaciones para email válido


    public UserUseCase(IUserPersistencePort userPersistencePort, IEncryptionServicePort encryptionServicePort, IRolePersistencePort rolePersistencePort) {
        this.userPersistencePort = userPersistencePort;
        this.encryptionServicePort = encryptionServicePort;
        this.rolePersistencePort = rolePersistencePort;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userPersistencePort.findByEmail(email);
    }

    @Override
    public void saveUser(UserModel userModel) {

        existValidator(userModel);
        validateBasic(userModel);
        encodePassword(userModel);
        userPersistencePort.createUser(userModel);

    }

    public static void validateBasic(UserModel userModel){

        validateFirstName(userModel.getFirstName());
        validateLastName(userModel.getLastName());
        isAdultValidate(userModel.getBirthDate());
        validatePhoneNumber(userModel.getPhoneNumber());
        validateEmail(userModel.getEmail());
        validatePassword(userModel.getPassword());
        validateIdentityDocument(userModel.getIdentityDocument());


    }

    public static void isAdultValidate(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new AgeValidationException(CreateUserValidationConstants.BIRTH_DATE_NULL_EXCEPTION) ;} // Retorna false si la fecha es nula
        LocalDate hoy = LocalDate.now(); // Fecha actual
        Period edad = Period.between(fechaNacimiento, hoy); // Calcula la diferencia
        if (edad.getYears() < 18){
            throw new AgeValidationException(CreateUserValidationConstants.BIRT_DATE_ADULT) ;
        } // Retorna true si tiene 18 años o más
    }

    public static void validatePhoneNumber(String input) {
        // Verifica si el String es nulo o vacío
        if (input == null || input.isEmpty()) {
            throw new PhoneNumberValidation(CreateUserValidationConstants.PHONE_NULL_EXCEPTION);
        }

        String regex = "^\\+?[\\d+]{1,13}$"; // Patrón para validar el número de teléfono
        if (!input.matches(regex)) {
            throw new PhoneNumberValidation(CreateUserValidationConstants.PHONE_VALID);
        }

        if (input.length() < 10) {
            throw new PhoneNumberValidation(CreateUserValidationConstants.PHONE_MIN_SIZE_EXCEPTION);
        } else if (input.length() > 13) {
            throw new PhoneNumberValidation(CreateUserValidationConstants.PHONE_MAX_SIZE_EXCEPTION);
        }
        // Comprobar cada carácter del String
        /*for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                throw new PhoneNumberValidation(CreateUserValidationConstants.PHONE_ONLY_NUMBER); // Retorna false si encuentra un carácter no numérico
            }
        }*/

    }

    public static void validateEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmailValidationException(CreateUserValidationConstants.EMAIL_NULL_EXCEPTION); // Retorna false si el String es nulo o vacío
        }
        if(CreateUserValidationConstants.EMAIL_PATTERN.matches(email)) {
            throw new EmailValidationException(CreateUserValidationConstants.EMAIL_VALID);
        }// Verifica si el email coincide con el patrón
        if (email.length() < 5) {
            throw new EmailValidationException(CreateUserValidationConstants.EMAIL_MIN_SIZE_EXCEPTION);
        }
        if (email.length() > 250) {
            throw new EmailValidationException(CreateUserValidationConstants.EMAIL_MAX_SIZE_EXCEPTION);
        }

    }

    public static void validateFirstName(String firstName) {
        if (firstName == null || firstName.isEmpty()) {
            throw new IllegalArgumentException(CreateUserValidationConstants.NAME_NOT_NULL_EXCEPTION); // Retorna false si el String es nulo o vacío
        }
        if (firstName.length() < 2) {
            throw new IllegalArgumentException(CreateUserValidationConstants.NAME_MIN_SIZE_EXCEPTION);
        }
        if (firstName.length() > 50) {
            throw new IllegalArgumentException(CreateUserValidationConstants.NAME_MAX_SIZE_EXCEPTION);
        }
    }

    public static void validateLastName(String lastName) {
        if (lastName == null || lastName.isEmpty()) {
            throw new IllegalArgumentException(CreateUserValidationConstants.LAST_NAME_NULL_EXCEPTION); // Retorna false si el String es nulo o vacío
        }
        if (lastName.length() < 2) {
            throw new IllegalArgumentException(CreateUserValidationConstants.LAST_NAME_MIN_SIZE_EXCEPTION);
        }
        if (lastName.length() > 50) {
            throw new IllegalArgumentException(CreateUserValidationConstants.LAST_NAME_MAX_SIZE_EXCEPTION);
        }
    }

    public static void validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException(CreateUserValidationConstants.PASSWORD_NULL_EXCEPTION); // Retorna false si el String es nulo o vacío
        }
        if (password.length() < 5) {
            throw new IllegalArgumentException(CreateUserValidationConstants.PASSWORD_MIN_SIZE_EXCEPTION);
        }
        if (password.length() > 20) {
            throw new IllegalArgumentException(CreateUserValidationConstants.PASSWORD_MAX_SIZE_EXCEPTION);
        }
    }

    public static void validateIdentityDocument(String identityDocument) {

        if (identityDocument == null || identityDocument.isEmpty()) {
            throw new IllegalArgumentException(CreateUserValidationConstants.IDENTITY_DOCUMENT_NULL_EXCEPTION); // Retorna false si el String es nulo o vacío
        }
        if (identityDocument.length() < 8) {
            throw new IllegalArgumentException(CreateUserValidationConstants.IDENTITY_DOCUMENT_MIN_SIZE_EXCEPTION);
        }
        if (identityDocument.length() > 13) {
            throw new IllegalArgumentException(CreateUserValidationConstants.IDENTITY_DOCUMENT_MAX_SIZE_EXCEPTION);
        }

    }

    /*if (userPersistencePort.findByEmail(userModel.getEmail()).isPresent()) {
            throw new UserAlreadyExist();
        } else if (!validatePhoneNumber(userModel.getPhoneNumber())) {
            throw new PhoneNumberIsNotValid();
        } else if ( userModel.getPhoneNumber().length()>13 && userModel.getPhoneNumber().length()<10) {
            throw new PhoneNumberLengthIsNotValid();
        } else if (userPersistencePort.existsUserByPhoneNumber(userModel.getPhoneNumber())) {
            throw new PhoneNumberAlreadyIsUse();
        } else if (!validateOnlyNumber(userModel.getIdentityDocument())) {
            throw new DocumentIdentityOnlyNumeric();
        } else if (!validateEmail(userModel.getEmail())) {
            throw new EmailIsNotValid();
        } else if (!isAdultValidate(userModel.getBirthDate())) {
            throw new UserIsNotAdult();
        } else {
            userPersistencePort.createUser(userModel);
        }*/
    private void existValidator(UserModel userModel) {

        if (userPersistencePort.findByEmail(userModel.getEmail()).isPresent()) {
            throw new EmailValidationException(CreateUserValidationConstants.EMAIL_EXISTS_EXCEPTION);
        }

        if (userPersistencePort.findByPhoneNumber(userModel.getPhoneNumber()).isPresent()) {
            throw new PhoneNumberValidation(CreateUserValidationConstants.PHONE_EXISTS_EXCEPTION);
        }

        if (userPersistencePort.findByIdentityDocument(userModel.getIdentityDocument()).isPresent()) {
            throw new IllegalArgumentException(CreateUserValidationConstants.IDENTITY_DOCUMENT_EXISTS_EXCEPTION);
        }
    }

    public void encodePassword(UserModel userModel){
        userModel.setPassword(encryptionServicePort.encode(userModel.getPassword()));
    }

}
