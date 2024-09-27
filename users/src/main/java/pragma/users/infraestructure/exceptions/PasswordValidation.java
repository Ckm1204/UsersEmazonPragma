package pragma.users.infraestructure.exceptions;

public class PasswordValidation extends RuntimeException {
    public PasswordValidation(String message) {
        super(message);
    }
}
