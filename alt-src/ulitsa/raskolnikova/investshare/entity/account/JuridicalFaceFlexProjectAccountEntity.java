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
public class JuridicalFaceFlexProjectAccountEntity extends FlexProjectAccountEntity {
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

    @Column(name = "svid_o_gos_reg_jur_face")
    private byte[] svidOGosRegJurFace;

    @Column(name = "svid_o_post_na_inn")
    private byte[] svidOPostNaINN;

    @Column(name = "prot_o_nazn_lica")
    private byte[] protONaznLica;

    @Column(name = "uved_o_post_na_usn")
    private byte[] uvedOPostNaUSN;

    @Column(name = "deistv_na_osnovanii")
    private byte[] deistvNaOsnovanii;
}
