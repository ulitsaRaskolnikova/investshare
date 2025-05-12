package ulitsa.raskolnikova.investshare.dto.project.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class JuridicalFaceProjectAccount extends ProjectAccount {
    private String actsOnTheBasisOf;
    private String representedBy;
    private String fullNameOfTheOrganization;
    private String abbreviatedNameOfTheOrganization;
    @JsonProperty("OGRN")
    private String ogrn;
    @JsonProperty("KPP")
    private String kpp;
    private String legalAddress;
    private String actualAddress;

    private byte[] svidOGosRegJurFace;
    private byte[] svidOPostNaINN;
    private byte[] protONaznLica;
    private byte[] uvedOPostNaUSN;
    private byte[] deistvNaOsnovanii;
}
