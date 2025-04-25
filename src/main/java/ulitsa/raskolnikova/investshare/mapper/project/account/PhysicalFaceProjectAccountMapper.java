package ulitsa.raskolnikova.investshare.mapper.project.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.project.account.PhysicalFaceProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.PhysicalFaceProjectAccountEntity;

@Mapper(componentModel = "spring")
public interface PhysicalFaceProjectAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    PhysicalFaceProjectAccountEntity toEntity(PhysicalFaceProjectAccount dto);

    PhysicalFaceProjectAccount toDto(PhysicalFaceProjectAccountEntity entity);
}
