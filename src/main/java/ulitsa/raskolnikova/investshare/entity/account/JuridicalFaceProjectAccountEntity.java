package ulitsa.raskolnikova.investshare.entity.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "juridical_face_project_account")
@Entity
public class JuridicalFaceProjectAccountEntity extends ProjectAccountEntity {
    @Column(name = "acts_on_base", length = Integer.MAX_VALUE)
    private String actsOnTheBasisOf;

    @Column(name = "represented_by", length = Integer.MAX_VALUE)
    private String representedBy;

    @Column(name = "full_organisation_name", length = Integer.MAX_VALUE)
    private String fullNameOfTheOrganization;

    @Column(name = "abbreviated_name_of_organisation", length = Integer.MAX_VALUE)
    private String abbreviatedNameOfTheOrganization;

    @Column(name = "ogrn")
    private String ogrn;

    @Column(name = "kpp", length = Integer.MAX_VALUE)
    private String kpp;

    @Column(name = "legal_address", length = Integer.MAX_VALUE)
    private String legalAddress;

    @Column(name = "actual_address", length = Integer.MAX_VALUE)
    private String actualAddress;
}
