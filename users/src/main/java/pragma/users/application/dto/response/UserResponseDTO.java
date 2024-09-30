package pragma.users.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String name;

    private String lastName;

    private String identityDocument;

    private String phone;

    private String email;

    private String password;

    private LocalDate birthDate;
}
