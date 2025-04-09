package ulitsa.raskolnikova.investshare.entity.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "ip_project_account")
@Entity
public class IpProjectAccountEntity extends ProjectAccountEntity {
    @Column(name = "ip_svid_serial")
    private Integer ipSvidSerial;

    @Column(name = "ip_svid_number")
    private Integer ipSvidNumber;

    @Column(name = "ip_svid_givenby", length = Integer.MAX_VALUE)
    private String ipSvidGivenby;

    @Column(name = "ogrn")
    private Integer ogrn;

    @Column(name = "jur_address", length = Integer.MAX_VALUE)
    private String jurAddress;

    @Column(name = "fact_address", length = Integer.MAX_VALUE)
    private String factAddress;

    @Column(name = "svid_o_postanovke_na_nalog_uchet")
    private byte[] svidOPostanovkeNaNalogUchet;

    @Column(name = "ip_passport_photo_page")
    private byte[] ipPassportPhotoPage;

    @Column(name = "ip_passport_propiska")
    private byte[] ipPassportPropiska;

    @Column(name = "usn")
    private byte[] usn;

    @Column(name = "ogrnip")
    private byte[] ogrnip;
}
