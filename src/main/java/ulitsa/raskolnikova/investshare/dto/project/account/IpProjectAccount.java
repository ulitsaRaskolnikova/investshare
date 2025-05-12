package ulitsa.raskolnikova.investshare.dto.project.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class IpProjectAccount extends ProjectAccount {
    @JsonProperty("IECertificateSeries")
    private String ieCertificateSeries;
    @JsonProperty("IECertificateNumber")
    private String ieCertificateNumber;
    @JsonProperty("IECertificateIssued")
    private String ieCertificateIssued;
    @JsonProperty("OGRN")
    private String ogrn;
    private String legalAddress;
    private String actualAddress;

    private byte[] passportPageWithPhoto;
    private byte[] passportPageWithPropiska;
    private byte[] svidOPostanovkeNaUchetPhysLitsa;
    private byte[] svidORegIndivPredp;
    private byte[] uvedOPostNaUSN;
}
