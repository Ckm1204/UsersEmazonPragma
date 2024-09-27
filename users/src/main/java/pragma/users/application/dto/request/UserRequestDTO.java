package pragma.users.application.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserRequestDTO {

    private String firstName;
    private String lastName;
    private String identityDocument;
    private String phoneNumber;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Pattern(regexp = "^\\d{2}/\\d{2}/\\d{4}$", message = "Birthdate must be in the format dd/MM/yyyy")
    private LocalDate birthDate;
    private String email;
    private String password;
    private Integer role;

    public UserRequestDTO() {
    }

    public UserRequestDTO(Integer role,String firstName, String lastName, String identityDocument, String phoneNumber, LocalDate birthDate, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityDocument = identityDocument;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.role = role;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = identityDocument;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
