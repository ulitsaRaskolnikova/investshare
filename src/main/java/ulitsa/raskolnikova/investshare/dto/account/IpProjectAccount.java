package ulitsa.raskolnikova.investshare.dto.account;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class IpProjectAccount extends ProjectAccount {
    private Integer ipSvidSerial;
    private Integer ipSvidNumber;
    private String ipSvidGivenby;
    private Integer ogrn;
    private String jurAddress;
    private String factAddress;

    private byte[] svidOPostanovkeNaNalogUchet;
    private byte[] ipPassportPhotoPage;
    private byte[] ipPassportPropiska;
    private byte[] usn;
    private byte[] ogrnip;
}
