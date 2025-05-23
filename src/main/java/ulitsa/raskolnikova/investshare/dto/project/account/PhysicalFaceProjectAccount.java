package ulitsa.raskolnikova.investshare.dto.project.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PhysicalFaceProjectAccount extends ProjectAccount {
    private String passportSeries;
    private String passportNumber;
    private String passportGivenBy;
    private String registrationAddress;
    private byte[] passportPageWithPhoto;
    private byte[] passportPageWithPropiska;
    private byte[] svidOPostanovkeNaUchetPhysLitsa;
}