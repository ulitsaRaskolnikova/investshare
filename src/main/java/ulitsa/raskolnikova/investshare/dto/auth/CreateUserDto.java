package ulitsa.raskolnikova.investshare.dto.auth;

public record CreateUserDto(
        String username,
        byte[] avatar,
        String email,
        String password
) {}
