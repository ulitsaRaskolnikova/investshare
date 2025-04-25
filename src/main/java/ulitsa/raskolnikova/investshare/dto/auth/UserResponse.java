package ulitsa.raskolnikova.investshare.dto.auth;

import java.math.BigDecimal;
import java.time.Instant;

public record UserResponse(
        String username,
        byte[] avatar,
        String email,
        BigDecimal balance,
        Instant createdAt
) {
}
