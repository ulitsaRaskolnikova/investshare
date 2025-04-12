package ulitsa.raskolnikova.investshare.dto;

import ulitsa.raskolnikova.investshare.dto.account.ProjectAccount;

import java.math.BigDecimal;

public record ProjectResponse(
    Integer id,
    Integer authorId,
    String name,
    String quickPeek,
    byte[] quickPeekPicture,
    String pollAddress,
    String content,
    Boolean isCompleted,
    BigDecimal currentMoney,
    BigDecimal wantedMoney,
    Integer duration
) implements ResponseValue {}
