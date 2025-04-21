package ulitsa.raskolnikova.investshare.dto;

import ulitsa.raskolnikova.investshare.dto.account.ProjectAccount;

import java.math.BigDecimal;

public record ProjectResponse(
        Integer id,
        Integer authorId,
        String name,
        String quickPeek,
        byte[] quickPeekPicture,
        String category,
        String location,
        Integer durationDays,
        BigDecimal wantedMoney,
        Boolean isPublic,
        BigDecimal currentMoney
) implements ResponseValue {}
