package ulitsa.raskolnikova.investshare.mapper.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.account.JuridicalFaceProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.JuridicalFaceProjectAccountEntity;

@Mapper(componentModel = "spring")
public interface JuridicalFaceProjectAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    JuridicalFaceProjectAccountEntity toEntity(JuridicalFaceProjectAccount dto);

    JuridicalFaceProjectAccount toDto(JuridicalFaceProjectAccountEntity entity);
}
