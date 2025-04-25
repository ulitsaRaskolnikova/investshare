package ulitsa.raskolnikova.investshare.mapper.project.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.project.account.IpProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.IpProjectAccountEntity;

@Mapper(componentModel = "spring")
public interface IpProjectAccountMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    IpProjectAccountEntity toEntity(IpProjectAccount dto);

    IpProjectAccount toDto(IpProjectAccountEntity entity);
}
