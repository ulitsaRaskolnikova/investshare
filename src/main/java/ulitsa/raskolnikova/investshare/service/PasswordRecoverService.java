package ulitsa.raskolnikova.investshare.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import ulitsa.raskolnikova.investshare.entity.PasswordResetToken;
import ulitsa.raskolnikova.investshare.entity.UserEntity;
import ulitsa.raskolnikova.investshare.exception.ExpiredRecoverPasswordTokenException;
import ulitsa.raskolnikova.investshare.repository.PasswordTokenRepository;
import ulitsa.raskolnikova.investshare.repository.UserEntityRepository;
import ulitsa.raskolnikova.investshare.util.PasswordHasher;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PasswordRecoverService {
    private final UserEntityRepository userEntityRepository;
    private final PasswordTokenRepository passwordTokenRepository;
    private final PasswordHasher passwordHasher;
    private final MailSender mailSender;
    @Value("${spring.mail.username}")
    private String supportEmail;
    @Value("${recover.url}")
    private String recoverUrl;

    public void changePassword(String token, String password) {
        PasswordResetToken passwordResetToken = passwordTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new NoSuchElementException("Token not found"));
        if (passwordResetToken.getExpiryDate().isBefore(Instant.now())) {
            passwordTokenRepository.delete(passwordResetToken);
            throw new ExpiredRecoverPasswordTokenException("Expired recover password token");
        }
        UserEntity user = passwordResetToken.getUser();
        user.setPassword(passwordHasher.hashPassword(user.getEmail(), password));
        passwordTokenRepository.delete(passwordResetToken);
        userEntityRepository.save(user);
    }

    public void sendRecoverEmail(String email) {
        UserEntity user = userEntityRepository.findByEmail(email).orElseThrow(() ->
                new NoSuchElementException("Not found email: " + email));
        PasswordResetToken passwordResetToken = new PasswordResetToken(UUID.randomUUID().toString(), user);
        passwordResetToken.setExpiryDate(Instant.now().plusSeconds(PasswordResetToken.EXPIRATION));
        mailSender.send(constructEmail(email, passwordResetToken.getToken()));
        passwordTokenRepository.save(passwordResetToken);
    }

    private SimpleMailMessage constructEmail(String email, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Восстановление пароля");
        message.setText("Для восстановления пароля перейдите по ссылке: "
                + recoverUrl + "?token=" + token
                + "\nЕсли вы не запрашивали восстановление пароля, то просто проигнорируйте это письмо.");
        message.setTo(email);
        message.setFrom(supportEmail);
        return message;
    }
}
