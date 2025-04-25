package ulitsa.raskolnikova.investshare.dto.project;

import lombok.Data;
import ulitsa.raskolnikova.investshare.dto.project.account.ProjectAccount;

import java.math.BigDecimal;

@Data
public class Project implements ResponseValue {
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
    private ProjectAccount projectAccount;
    private BigDecimal currentMoney;
}
