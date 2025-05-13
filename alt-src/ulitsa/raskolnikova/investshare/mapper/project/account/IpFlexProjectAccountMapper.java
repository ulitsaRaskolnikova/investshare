package ulitsa.raskolnikova.investshare.mapper.project.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.project.account.IpFlexProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.IpFlexProjectAccountEntity;

@Mapper(componentModel = "spring")
public interface IpFlexProjectAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    IpFlexProjectAccountEntity toEntity(IpFlexProjectAccount dto);

    IpFlexProjectAccount toDto(IpFlexProjectAccountEntity entity);
}
