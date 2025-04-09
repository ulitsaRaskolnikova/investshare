package ulitsa.raskolnikova.investshare.dto.account;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class JuridicalFaceProjectAccount extends ProjectAccount {
    private String actsOnBase;
    private String position;
    private String fullOrganisationName;
    private String shortOrganisationName;
    private Integer ogrn;
    private String kpp;
    private String jurAddress;
    private String factAddress;
    private byte[] svidORegistratsiiJurLitsa;
    private byte[] svidOPostanovkeNaNalogUchet;
    private byte[] protocolONasznacheniiLitsa;
    private byte[] usn;
    private byte[] ustav;
}
