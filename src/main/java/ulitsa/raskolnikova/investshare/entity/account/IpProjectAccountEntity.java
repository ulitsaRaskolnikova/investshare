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
    @Column(name = "ie_certificate_series")
    private String ieCertificateSeries;

    @Column(name = "ie_certificate_number")
    private String ieCertificateNumber;

    @Column(name = "ie_certificate_issued", length = Integer.MAX_VALUE)
    private String ieCertificateIssued;

    @Column(name = "ogrn")
    private String ogrn;

    @Column(name = "legal_address", length = Integer.MAX_VALUE)
    private String legalAddress;

    @Column(name = "actual_address", length = Integer.MAX_VALUE)
    private String actualAddress;
}
