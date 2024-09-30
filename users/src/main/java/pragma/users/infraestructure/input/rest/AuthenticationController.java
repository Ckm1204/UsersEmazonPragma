package pragma.users.infraestructure.input.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pragma.users.application.dto.request.AuthenticationLogin;
import pragma.users.application.dto.response.AuthenticationResponse;
import pragma.users.application.service.IAuthService;
import pragma.users.infraestructure.configuration.openApiSwagger.ConstantsOpenApi;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "Endpoints for authentication")
public class AuthenticationController {

    private final IAuthService authService;

    public AuthenticationController(IAuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Authenticate user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = ConstantsOpenApi.CODE_STATUS_201, description = ConstantsOpenApi.DESCRIPTION_STATUS_201, content = @Content(schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(responseCode = ConstantsOpenApi.CODE_STATUS_403, description = ConstantsOpenApi.DESCRIPTION_STATUS_403, content = @Content),
    })
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationLogin request) {
        AuthenticationResponse jwtDto = authService.login(request);
        return ResponseEntity.ok(jwtDto);
    }
}
