package ulitsa.raskolnikova.investshare.mapper.project.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.project.account.JuridicalFaceFlexProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.JuridicalFaceFlexProjectAccountEntity;

@Mapper(componentModel = "spring")
public interface JuridicalFaceFlexProjectAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    JuridicalFaceFlexProjectAccountEntity toEntity(JuridicalFaceFlexProjectAccount dto);

    JuridicalFaceFlexProjectAccount toDto(JuridicalFaceFlexProjectAccountEntity entity);
}
