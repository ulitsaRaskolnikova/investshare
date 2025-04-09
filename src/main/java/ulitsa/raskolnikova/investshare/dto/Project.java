package ulitsa.raskolnikova.investshare.dto;

import lombok.Data;
import ulitsa.raskolnikova.investshare.dto.account.ProjectAccount;

import java.math.BigDecimal;

@Data
public class Project {
    private Integer id;
    private ProjectAccount projectAccount;
    private Integer authorId;
    private String name;
    private String quickPeek;
    private byte[] quickPeekPicture;
    private String pollAddress;
    private String content;
    private Boolean isPublic;
    private Boolean isCompleted;
    private BigDecimal currentMoney;
    private BigDecimal wantedMoney;
    private Integer duration;
}
