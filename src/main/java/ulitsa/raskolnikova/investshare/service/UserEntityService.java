package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.dto.Auth;
import ulitsa.raskolnikova.investshare.entity.PasswordResetToken;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;
import ulitsa.raskolnikova.investshare.repository.PasswordTokenRepository;
import ulitsa.raskolnikova.investshare.repository.UserEntityRepository;
import ulitsa.raskolnikova.investshare.util.PasswordEncoder;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserEntityService {
    private final PasswordEncoder passwordEncoder;
    private final UserEntityRepository userEntityRepository;

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getEmail(), userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public void login(String authorization) throws InvalidBasicAuthorizationException {
        getUserEntity(authorization);
    }

    public UserEntity getUserEntity(String authorization) throws InvalidBasicAuthorizationException {
        if (authorization == null) {
            throw new InvalidBasicAuthorizationException("Authorization is required");
        }
        Auth auth = passwordEncoder.decode(authorization);
        UserEntity user = userEntityRepository.findByEmail(auth.email())
                .orElseThrow(() -> new InvalidBasicAuthorizationException(auth.email() + " not found"));
        if (!user.getPassword().equals(authorization.split(" ")[1])) {
            throw new InvalidBasicAuthorizationException("Wrong password");
        }
        return user;
    }

}
