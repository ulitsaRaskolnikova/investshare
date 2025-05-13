package ulitsa.raskolnikova.investshare.entity.account;

import jakarta.persistence.*;
import lombok.Data;
import ulitsa.raskolnikova.investshare.entity.FlexProjectEntity;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class FlexProjectAccountEntity {
    @Id
    @Column(name = "project_id", nullable = false)
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "project_id")
    private FlexProjectEntity project;

    @Column(name = "bic")
    private String bic;  

    @Column(name = "ras_schot")
    private String rasSchot;  

    @Column(name = "kor_schot")
    private String korSchot;  

    @Column(name = "fio", length = Integer.MAX_VALUE)
    private String fio;

    @Column(name = "inn")
    private String inn;  

    @Column(name = "post_address", length = Integer.MAX_VALUE)
    private String postAddress;
}
