package ulitsa.raskolnikova.investshare.dto.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PhysicalFaceProjectAccount extends ProjectAccount {
    private Integer passportSeries;
    private Integer passportNumber;
    private String passportGivenBy;
    private String registrationAddress;
    private byte[] passportPageWithPhoto;
    private byte[] passportPageWithPropiska;
    private byte[] svidOPostanovkeNaUchetPhysLitsa;
}
