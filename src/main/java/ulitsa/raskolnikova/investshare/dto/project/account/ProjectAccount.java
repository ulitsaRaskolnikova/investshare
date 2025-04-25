package ulitsa.raskolnikova.investshare.dto.project.account;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;


@Data
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "accountType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PhysicalFaceProjectAccount.class, name = "PHYSICAL"),
        @JsonSubTypes.Type(value = JuridicalFaceProjectAccount.class, name = "LEGAL"),
        @JsonSubTypes.Type(value = IpProjectAccount.class, name = "ENTREPRENEUR")
})
public abstract class ProjectAccount {
    private String bic;
    private String rasSchot;
    private String korSchot;
    private String fio;
    private String inn;
    private String postAddress;
}