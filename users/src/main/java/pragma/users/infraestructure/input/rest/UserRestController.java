package pragma.users.infraestructure.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Controller", description = "Endpoints for user management")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create a new admin user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Admin user created successfully", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("admin")
    public ResponseEntity<Void> saveUserAdmin(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.createUserAdmin(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Create a new auxiliary warehouse user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Auxiliary warehouse user created successfully", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("auxBodega")
    public ResponseEntity<Void> saveUserAuxBodega(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.createUserAuxBodega(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    @PostMapping("user")
    public ResponseEntity<Void> saveUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        userService.createUser(userRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}