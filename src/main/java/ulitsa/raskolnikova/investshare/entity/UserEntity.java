package ulitsa.raskolnikova.investshare.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "\"user\"")
public class UserEntity implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username", length = Integer.MAX_VALUE)
    private String username;

    @Column(name = "avatar")
    private byte[] avatar;

    @Column(name = "email", length = Integer.MAX_VALUE)
    private String email;

    @Column(name = "balance", precision = 34, scale = 2)
    private BigDecimal balance;

    @Column(name = "password", nullable = false, length = Integer.MAX_VALUE)
    private String password;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToMany
    @JoinTable(
            name = "favoured_project_for_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<ProjectEntity> favouredProjects;

    @ManyToMany
    @JoinTable(
            name = "user_project_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<ProjectEntity> projects;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (balance == null) {
            balance = BigDecimal.ZERO;
        }
    }


}