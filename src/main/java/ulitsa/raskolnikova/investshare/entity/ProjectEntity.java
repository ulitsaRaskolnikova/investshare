package ulitsa.raskolnikova.investshare.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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

    @Column(name = "poll_address", length = Integer.MAX_VALUE)
    private String pollAddress;

    @Column(name = "content", length = Integer.MAX_VALUE)
    private String content;

    @ColumnDefault("true")
    @Column(name = "is_public")
    private Boolean isPublic;

    @ColumnDefault("false")
    @Column(name = "is_completed")
    private Boolean isCompleted;

    @Column(name = "current_money", precision = 34, scale = 2)
    private BigDecimal currentMoney;

    @Column(name = "wanted_money", precision = 34, scale = 2)
    private BigDecimal wantedMoney;

    @ColumnDefault("30")
    @Column(name = "duration")
    private Integer duration;
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