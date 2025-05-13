package ulitsa.raskolnikova.investshare.mapper.project;

import org.mapstruct.*;
import ulitsa.raskolnikova.investshare.dto.project.FlexProject;
import ulitsa.raskolnikova.investshare.dto.project.FlexProjectResponse;
import ulitsa.raskolnikova.investshare.entity.FlexProjectEntity;
import ulitsa.raskolnikova.investshare.mapper.project.account.FlexProjectAccountMapper;

@Mapper(componentModel = "spring", uses = FlexProjectAccountMapper.class)
public interface FlexProjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "devDiaries", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(source = "authorId", target = "author.id")
    FlexProjectEntity toEntity(FlexProject dto);

    @Mapping(source = "author.id", target = "authorId")
    FlexProject toDto(FlexProjectEntity projectEntity);

    FlexProjectResponse toResponseDto(FlexProject project);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "devDiaries", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "members", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFlexProjectFromDto(FlexProject dto, @MappingTarget FlexProjectEntity entity);
}
