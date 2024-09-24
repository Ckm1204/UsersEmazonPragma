package pragma.users.infraestructure.exceptionService;

public enum ExceptionUserResponse {


    USER_ALREADY_EXISTS("User already exists"),
    PHONE_NUMBER_IS_NOT_VALID("Phone number is not valid"),
    EMAIL_IS_NOT_VALID("Email is not valid"),
    PHONE_NUMBER_LENGTH_IS_NOT_VALID("Phone number length have to between 10 and 13"),
    PHONE_NUMBER_ALREADY_IS_USE("Phone number already is in use"),
    DOCUMENT_IDENTITY_ONLY_NUMERIC("Document identity only numeric"),
    USER_IS_NOT_ADULT("User is not adult"),
    // TODO add more exceptions
    ;

    private String message;

    ExceptionUserResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
