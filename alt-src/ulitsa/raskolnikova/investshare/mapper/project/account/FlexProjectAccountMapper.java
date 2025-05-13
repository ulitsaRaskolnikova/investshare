package ulitsa.raskolnikova.investshare.mapper.project.account;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;
import ulitsa.raskolnikova.investshare.dto.project.account.IpFlexProjectAccount;
import ulitsa.raskolnikova.investshare.dto.project.account.JuridicalFaceFlexProjectAccount;
import ulitsa.raskolnikova.investshare.dto.project.account.PhysicalFaceFlexProjectAccount;
import ulitsa.raskolnikova.investshare.dto.project.account.FlexProjectAccount;
import ulitsa.raskolnikova.investshare.entity.account.IpFlexProjectAccountEntity;
import ulitsa.raskolnikova.investshare.entity.account.JuridicalFaceFlexProjectAccountEntity;
import ulitsa.raskolnikova.investshare.entity.account.PhysicalFaceFlexProjectAccountEntity;
import ulitsa.raskolnikova.investshare.entity.account.FlexProjectAccountEntity;

@Mapper(componentModel = "spring")
public abstract class FlexProjectAccountMapper {
    @Autowired
    private IpFlexProjectAccountMapper ipFlexProjectAccountMapper;

    @Autowired
    private PhysicalFaceFlexProjectAccountMapper physicalFaceFlexProjectAccountMapper;

    @Autowired
    private JuridicalFaceFlexProjectAccountMapper juridicalFaceFlexProjectAccountMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    public FlexProjectAccountEntity toEntity(FlexProjectAccount dto) {
        if (dto instanceof PhysicalFaceFlexProjectAccount physical) {
            return physicalFaceFlexProjectAccountMapper.toEntity(physical);
        } else if (dto instanceof JuridicalFaceFlexProjectAccount juridical) {
            return juridicalFaceFlexProjectAccountMapper.toEntity(juridical);
        } else if (dto instanceof IpFlexProjectAccount ip) {
            return ipFlexProjectAccountMapper.toEntity(ip);
        }
        return null;
    }

    public FlexProjectAccount toDto(FlexProjectAccountEntity entity) {
        if (entity instanceof PhysicalFaceFlexProjectAccountEntity physical) {
            return physicalFaceFlexProjectAccountMapper.toDto(physical);
        } else if (entity instanceof JuridicalFaceFlexProjectAccountEntity juridical) {
            return juridicalFaceFlexProjectAccountMapper.toDto(juridical);
        } else if (entity instanceof IpFlexProjectAccountEntity ip) {
            return ipFlexProjectAccountMapper.toDto(ip);
        }
        return null;
    }


}

