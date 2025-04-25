package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.dto.auth.AuthenticationRequest;
import ulitsa.raskolnikova.investshare.dto.auth.AuthenticationToken;
import ulitsa.raskolnikova.investshare.dto.auth.CreateUserDto;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.mapper.user.UserMapper;
import ulitsa.raskolnikova.investshare.repository.UserEntityRepository;
import ulitsa.raskolnikova.investshare.util.PasswordHasher;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordHasher passwordHasher;

    public AuthenticationToken register(CreateUserDto request) {
        UserEntity userEntity = userMapper.toEntity(request);
        userEntity.setPassword(passwordHasher.hashPassword(userEntity.getEmail(), userEntity.getPassword()));
        UserEntity user = userEntityRepository.save(userEntity);
        return new AuthenticationToken(jwtService.generateToken(user));
    }

    public AuthenticationToken authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.email(), passwordHasher.hashPassword(request.email(), request.password())
        ));
        UserEntity user = userEntityRepository
                .findByEmail(request.email())
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + request.email() + " not found"));
        return new AuthenticationToken(jwtService.generateToken(user));
    }
}
