package ulitsa.raskolnikova.investshare.util;

import org.springframework.stereotype.Component;
import ulitsa.raskolnikova.investshare.dto.Auth;
import ulitsa.raskolnikova.investshare.exception.InvalidBasicAuthorizationException;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PasswordEncoder {
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();

    public String encode(String email, String password) {
        byte[] bytes = encoder.encode((email + ":" + password).getBytes(StandardCharsets.UTF_8));
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public Auth decode(String authorization) throws InvalidBasicAuthorizationException {
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
}
