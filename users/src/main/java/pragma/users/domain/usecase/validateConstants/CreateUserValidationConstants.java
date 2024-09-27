package pragma.users.domain.usecase.validateConstants;

import java.util.regex.Pattern;

public class CreateUserValidationConstants {


    public static final String NAME_NOT_NULL_EXCEPTION = "Name is required";

    public static final String LAST_NAME_NULL_EXCEPTION = "Last name is required";

    public static final String IDENTITY_DOCUMENT_NULL_EXCEPTION = "Identity document is required";

    public static final String PHONE_NULL_EXCEPTION = "Phone is required";

    public static final String EMAIL_NULL_EXCEPTION = "Email is required";

    public static final String PASSWORD_NULL_EXCEPTION = "Password is required";

    public static final String BIRTH_DATE_NULL_EXCEPTION = "Birth date is required";

    public static final String NAME_MIN_SIZE_EXCEPTION = "Name must have at least 2 characters";
    public static final String NAME_MAX_SIZE_EXCEPTION = "Name must have at most 50 characters";

    public static final String LAST_NAME_MIN_SIZE_EXCEPTION = "Last name must have at least 2 characters";
    public static final String LAST_NAME_MAX_SIZE_EXCEPTION = "Last name must have at most 50 characters";

    public static final String PASSWORD_MIN_SIZE_EXCEPTION = "Password must have at least 6 characters";
    public static final String PASSWORD_MAX_SIZE_EXCEPTION = "Password must have at most 20 characters";

    public static final String IDENTITY_DOCUMENT_EXISTS_EXCEPTION = "Identity document already in use";
    public static final String IDENTITY_DOCUMENT_MIN_SIZE_EXCEPTION = "Identity document must have at least 8 characters";
    public static final String IDENTITY_DOCUMENT_MAX_SIZE_EXCEPTION = "Identity document must have at most 13 characters";

    public static final String PHONE_EXISTS_EXCEPTION = "Identity document must be valid";
    public static final String PHONE_ONLY_NUMBER = "Identity document must be valid";
    public static final String PHONE_VALID = "Phone must contain a maximum of 13 digits and can contain a + at the beginning";
    public static final String PHONE_MIN_SIZE_EXCEPTION = "Phone must have at least 10 characters";
    public static final String PHONE_MAX_SIZE_EXCEPTION = "Phone must have at most 13 characters";

    public static final String EMAIL_MAX_SIZE_EXCEPTION = "Email must have at most 250 characters";
    public static final String EMAIL_EXISTS_EXCEPTION = "Email already exists";
    public static final String EMAIL_MIN_SIZE_EXCEPTION = "Email must have at least 5 characters";
    public static final String EMAIL_VALID = "Email must be valid";

    public static final String BIRTH_DATE_VALID = "Birth date must be valid";
    public static final String BIRT_DATE_ADULT = "User must be adult";

    public static final String EMAIL_REGEX = "^[\\w-\\.]+@[\\w-]+\\.[a-z]{2,}$";
    public static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?`{|}~^.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$" ;


    private CreateUserValidationConstants() {
        throw new IllegalStateException("Utility class");
    }






}
