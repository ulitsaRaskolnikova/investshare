package ulitsa.raskolnikova.investshare.mapper.account;

import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;
import ulitsa.raskolnikova.investshare.dto.Project;
import ulitsa.raskolnikova.investshare.dto.account.IpProjectAccount;
import ulitsa.raskolnikova.investshare.dto.account.JuridicalFaceProjectAccount;
import ulitsa.raskolnikova.investshare.dto.account.PhysicalFaceProjectAccount;
import ulitsa.raskolnikova.investshare.dto.account.ProjectAccount;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;
import ulitsa.raskolnikova.investshare.entity.account.IpProjectAccountEntity;
import ulitsa.raskolnikova.investshare.entity.account.JuridicalFaceProjectAccountEntity;
import ulitsa.raskolnikova.investshare.entity.account.PhysicalFaceProjectAccountEntity;
import ulitsa.raskolnikova.investshare.entity.account.ProjectAccountEntity;

@Mapper(componentModel = "spring")
public abstract class ProjectAccountMapper {
    @Autowired
    private IpProjectAccountMapper ipProjectAccountMapper;

    @Autowired
    private PhysicalFaceProjectAccountMapper physicalFaceProjectAccountMapper;

    @Autowired
    private JuridicalFaceProjectAccountMapper juridicalFaceProjectAccountMapper;

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "project", ignore = true)
    public ProjectAccountEntity toEntity(ProjectAccount dto) {
        if (dto instanceof PhysicalFaceProjectAccount physical) {
            return physicalFaceProjectAccountMapper.toEntity(physical);
        } else if (dto instanceof JuridicalFaceProjectAccount juridical) {
            return juridicalFaceProjectAccountMapper.toEntity(juridical);
        } else if (dto instanceof IpProjectAccount ip) {
            return ipProjectAccountMapper.toEntity(ip);
        }
        return null;
    }

    public ProjectAccount toDto(ProjectAccountEntity entity) {
        if (entity instanceof PhysicalFaceProjectAccountEntity physical) {
            return physicalFaceProjectAccountMapper.toDto(physical);
        } else if (entity instanceof JuridicalFaceProjectAccountEntity juridical) {
            return juridicalFaceProjectAccountMapper.toDto(juridical);
        } else if (entity instanceof IpProjectAccountEntity ip) {
            return ipProjectAccountMapper.toDto(ip);
        }
        return null;
    }


}

