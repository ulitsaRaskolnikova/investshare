package ulitsa.raskolnikova.investshare.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordHasher {
    @Value("${password.salt}")
    private String salt;
    private final PasswordEncoder passwordEncoder;

    public String hashPassword(String email, String password) {
        return passwordEncoder.encode(email.substring(0, email.length() - 1) + password + salt);
    }
}
