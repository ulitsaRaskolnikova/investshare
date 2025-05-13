package ulitsa.raskolnikova.investshare.mapper.project.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.project.account.PhysicalFaceFlexProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.PhysicalFaceFlexProjectAccountEntity;

@Mapper(componentModel = "spring")
public interface PhysicalFaceFlexProjectAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    PhysicalFaceFlexProjectAccountEntity toEntity(PhysicalFaceFlexProjectAccount dto);

    PhysicalFaceFlexProjectAccount toDto(PhysicalFaceFlexProjectAccountEntity entity);
}
