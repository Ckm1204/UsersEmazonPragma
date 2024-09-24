package pragma.users.infraestructure.input.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("")
    public ResponseEntity<Void> saveCategoryInStock(@RequestBody UserRequestDTO userRequestDTO) {
        userService.createUserAdmin(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }






}
