package pragma.users.infraestructure.input.rest;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pragma.users.application.dto.request.UserRequestDTO;
import pragma.users.application.service.UserService;

@RestController
@RequestMapping("/users/")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin")
    public ResponseEntity<Void> saveUserAdmin(@RequestBody UserRequestDTO userRequestDTO) {
        userService.createUserAdmin(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("auxBodega")
    public ResponseEntity<Void> saveUserAuxBodega(@RequestBody  UserRequestDTO userRequestDTO) {
        userService.createUserAuxBodega(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("user")
    public ResponseEntity<Void> saveUser(@RequestBody  UserRequestDTO userRequestDTO) {
        userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
