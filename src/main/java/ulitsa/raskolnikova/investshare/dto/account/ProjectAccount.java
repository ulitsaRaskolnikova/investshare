package ulitsa.raskolnikova.investshare.dto.account;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "accountType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PhysicalFaceProjectAccount.class, name = "PHYSICAL"),
        @JsonSubTypes.Type(value = JuridicalFaceProjectAccount.class, name = "JURIDICAL"),
        @JsonSubTypes.Type(value = IpProjectAccount.class, name = "IP")
})
@Data
public abstract class ProjectAccount {
    private Integer id;
    private Integer bic;
    private Integer rasSchot;
    private Integer korSchot;
    private String fio;
    private Integer inn;
    private String postAddress;
}
