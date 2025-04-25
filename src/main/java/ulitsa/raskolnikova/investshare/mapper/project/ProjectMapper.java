package ulitsa.raskolnikova.investshare.mapper.project;

import org.mapstruct.*;
import ulitsa.raskolnikova.investshare.dto.project.Project;
import ulitsa.raskolnikova.investshare.dto.project.ProjectResponse;
import ulitsa.raskolnikova.investshare.entity.ProjectEntity;
import ulitsa.raskolnikova.investshare.mapper.project.account.ProjectAccountMapper;

@Mapper(componentModel = "spring", uses = ProjectAccountMapper.class)
public interface ProjectMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "devDiaries", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(source = "authorId", target = "author.id")
    ProjectEntity toEntity(Project dto);

    @Mapping(source = "author.id", target = "authorId")
    Project toDto(ProjectEntity projectEntity);

    ProjectResponse toResponseDto(Project project);

    @Mapping(source = "authorId", target = "author.id")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "devDiaries", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(target = "members", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateProjectFromDto(Project dto, @MappingTarget ProjectEntity entity);
}
