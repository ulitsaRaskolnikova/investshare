package ulitsa.raskolnikova.investshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;
import ulitsa.raskolnikova.investshare.service.PasswordRecoverService;
import ulitsa.raskolnikova.investshare.service.UserEntityService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/auth")
public class RegistrationController {
    private final UserEntityService userEntityService;
    private final PasswordRecoverService passwordRecoverService;

    @PostMapping("/register")
    public ResponseEntity<UserEntity> register(@RequestBody UserEntity user) {
        return ResponseEntity.ok(userEntityService.createUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestHeader String authorization) throws InvalidBasicAuthorizationException {
        userEntityService.login(authorization);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> getCurrentUser(@RequestHeader String authorization)
            throws InvalidBasicAuthorizationException {
        return ResponseEntity.ok(userEntityService.getUserEntity(authorization));
    }

    @PostMapping("/recover")
    public ResponseEntity<Void> recover(@RequestParam String email) throws InvalidBasicAuthorizationException {
        passwordRecoverService.sendRecoverEmail(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recover")
    public ResponseEntity<Void> changePassword(@RequestParam String token, @RequestParam String password) {
        passwordRecoverService.changePassword(token, password);
        return ResponseEntity.ok().build();
    }
}
