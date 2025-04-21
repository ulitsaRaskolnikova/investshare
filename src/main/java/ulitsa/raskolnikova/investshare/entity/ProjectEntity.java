package ulitsa.raskolnikova.investshare.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import ulitsa.raskolnikova.investshare.entity.account.ProjectAccountEntity;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@Entity
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(mappedBy = "project", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private ProjectAccountEntity projectAccount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "quick_peek", length = Integer.MAX_VALUE)
    private String quickPeek;

    @Column(name = "quick_peek_picture")
    private byte[] quickPeekPicture;

    @Column(name = "category", length = 255)
    private String category;

    @Column(name = "location", length = 255)
    private String location;

    @ColumnDefault("30")
    @Column(name = "duration_days")
    private Integer durationDays;

    @Column(name = "wanted_money", precision = 34, scale = 2)
    private BigDecimal wantedMoney;

    @ColumnDefault("true")
    @Column(name = "is_public")
    private Boolean isPublic;

    @Column(name = "current_money", precision = 34, scale = 2)
    private BigDecimal currentMoney;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL )
    private List<CommentEntity> comments;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<DevDiaryEntity> devDiaries;

    @ManyToMany(mappedBy = "favouredProjects")
    private List<UserEntity> followers;

    @ManyToMany(mappedBy = "projects")
    private List<UserEntity> members;
}
