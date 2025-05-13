package ulitsa.raskolnikova.investshare.dto.project;

import lombok.Data;
import ulitsa.raskolnikova.investshare.dto.project.account.FlexProjectAccount;

import java.math.BigDecimal;

@Data
public class FlexProject implements ResponseValue {
    private Integer id;
    private Integer authorId;
    private String name;
    private String quickPeek;
    private byte[] quickPeekPicture;
    private String category;
    private String location;
    private Integer durationDays;
    private BigDecimal wantedMoney;
    private Boolean isPublic;
    private FlexProjectAccount projectAccount;
    private BigDecimal currentMoney;
}
