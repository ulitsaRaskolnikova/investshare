package ulitsa.raskolnikova.investshare.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ulitsa.raskolnikova.investshare.dto.auth.AuthenticationRequest;
import ulitsa.raskolnikova.investshare.dto.auth.AuthenticationToken;
import ulitsa.raskolnikova.investshare.dto.auth.CreateUserDto;
import ulitsa.raskolnikova.investshare.dto.auth.UserResponse;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;
import ulitsa.raskolnikova.investshare.mapper.user.UserMapper;
import ulitsa.raskolnikova.investshare.service.AuthenticationService;
import ulitsa.raskolnikova.investshare.service.PasswordRecoverService;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/auth")
public class AuthenticationController {
    private final PasswordRecoverService passwordRecoverService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationToken> register(@RequestBody CreateUserDto user) {
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationToken> login(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping("/recover")
    public ResponseEntity<Void> recover(@RequestParam String email) {
        passwordRecoverService.sendRecoverEmail(email);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/recover")
    public ResponseEntity<Void> changePassword(@RequestParam String token, @RequestParam String password) {
        passwordRecoverService.changePassword(token, password);
        return ResponseEntity.ok().build();
    }
}
