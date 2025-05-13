package ulitsa.raskolnikova.investshare.dto.project;

import java.math.BigDecimal;

public record FlexProjectResponse(
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
