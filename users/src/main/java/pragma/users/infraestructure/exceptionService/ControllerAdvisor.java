package pragma.users.infraestructure.exceptionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pragma.users.infraestructure.exceptionService.exceptions.*;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<Map<String, String>> userAlreadyExistService(
            UserAlreadyExist userAlreadyExist) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.USER_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(PhoneNumberIsNotValid.class)
    public ResponseEntity<Map<String, String>> PhoneIsNotValidService(
            PhoneNumberIsNotValid phoneNumberIsNotValid) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.PHONE_NUMBER_IS_NOT_VALID.getMessage()));
    }

    @ExceptionHandler(PhoneNumberLengthIsNotValid.class)
    public ResponseEntity<Map<String, String>> PhoneLengthIsNotValidService(
            PhoneNumberLengthIsNotValid phoneNumberLengthIsNotValid) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.PHONE_NUMBER_LENGTH_IS_NOT_VALID.getMessage()));
    }

    @ExceptionHandler(PhoneNumberAlreadyIsUse.class)
    public ResponseEntity<Map<String, String>> PhoneAlreadyIsUseService(
            PhoneNumberAlreadyIsUse phoneNumberAlreadyIsUse) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.PHONE_NUMBER_ALREADY_IS_USE.getMessage()));
    }

    @ExceptionHandler(DocumentIdentityOnlyNumeric.class)
    public ResponseEntity<Map<String, String>> DocumentIdentityOnlyNumericService(
            DocumentIdentityOnlyNumeric documentIdentityOnlyNumeric) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.DOCUMENT_IDENTITY_ONLY_NUMERIC.getMessage()));
    }

    @ExceptionHandler(EmailIsNotValid.class)
    public ResponseEntity<Map<String, String>> EmailIsNotValidService(
            EmailIsNotValid emailIsNotValid) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.EMAIL_IS_NOT_VALID.getMessage()));
    }

    @ExceptionHandler(UserIsNotAdult.class)
    public ResponseEntity<Map<String, String>> UserIsNotAdultService(
            UserIsNotAdult userIsNotAdult) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap(MESSAGE, ExceptionUserResponse.USER_IS_NOT_ADULT.getMessage()));
    }

}