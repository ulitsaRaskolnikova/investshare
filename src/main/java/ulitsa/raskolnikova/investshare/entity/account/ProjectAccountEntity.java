package ulitsa.raskolnikova.investshare.entity.account;

import jakarta.persistence.*;
import lombok.Data;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class ProjectAccountEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "project_id")
    private ProjectEntity project;

    @Column(name = "bic")
    private Integer bic;

    @Column(name = "ras_schot")
    private Integer rasSchot;

    @Column(name = "kor_schot")
    private Integer korSchot;

    @Column(name = "fio", length = Integer.MAX_VALUE)
    private String fio;

    @Column(name = "inn")
    private Integer inn;

    @Column(name = "post_address", length = Integer.MAX_VALUE)
    private String postAddress;
}