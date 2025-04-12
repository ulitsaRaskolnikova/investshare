package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;
import ulitsa.raskolnikova.investshare.repository.UserEntityRepository;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserEntityService {
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();
    private final UserEntityRepository userEntityRepository;

    public UserEntity createUser(UserEntity userEntity) {
        userEntity.setPassword(encode(userEntity.getEmail(), userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    public void login(String authorization) throws InvalidBasicAuthorizationException {
        getUserEntity(authorization);
    }

    private String encode(String email, String password) {
        byte[] bytes = encoder.encode((email + ":" + password).getBytes(StandardCharsets.UTF_8));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private Auth decode(String authorization) throws InvalidBasicAuthorizationException {
        String[] encoded = authorization.split(" ");
        if (encoded.length != 2) {
            throw new InvalidBasicAuthorizationException("Invalid value of basic authorization");
        }
        byte[] bytes = decoder.decode(encoded[1]);
        String[] emailAndPassword = new String(bytes, StandardCharsets.UTF_8).split(":");
        if (emailAndPassword.length != 2) {
            throw new InvalidBasicAuthorizationException("Invalid value of basic authorization");
        }
        return new Auth(emailAndPassword[0], emailAndPassword[1]);
    }

    public UserEntity getUserEntity(String authorization) throws InvalidBasicAuthorizationException {
        if (authorization == null) {
            throw new InvalidBasicAuthorizationException("Authorization is required");
        }
        Auth auth = decode(authorization);
        UserEntity user = userEntityRepository.findByEmail(auth.email())
                .orElseThrow(() -> new InvalidBasicAuthorizationException(auth.email() + " not found"));
        if (!user.getPassword().equals(authorization.split(" ")[1])) {
            throw new InvalidBasicAuthorizationException("Wrong password");
        }
        return user;
    }

    private record Auth(String email, String password) {}
}
