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
    private String actsOnBase;

    @Column(name = "position", length = Integer.MAX_VALUE)
    private String position;

    @Column(name = "full_organisation_name", length = Integer.MAX_VALUE)
    private String fullOrganisationName;

    @Column(name = "short_organisation_name", length = Integer.MAX_VALUE)
    private String shortOrganisationName;

    @Column(name = "ogrn")
    private Integer ogrn;

    @Column(name = "kpp", length = Integer.MAX_VALUE)
    private String kpp;

    @Column(name = "jur_address", length = Integer.MAX_VALUE)
    private String jurAddress;

    @Column(name = "fact_address", length = Integer.MAX_VALUE)
    private String factAddress;

    @Column(name = "svid_o_registratsii_jur_litsa")
    private byte[] svidORegistratsiiJurLitsa;

    @Column(name = "svid_o_postanovke_na_nalog_uchet")
    private byte[] svidOPostanovkeNaNalogUchet;

    @Column(name = "protocol_o_nasznachenii_litsa")
    private byte[] protocolONasznacheniiLitsa;

    @Column(name = "usn")
    private byte[] usn;

    @Column(name = "ustav")
    private byte[] ustav;
}