package ulitsa.raskolnikova.investshare.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ulitsa.raskolnikova.investshare.dto.auth.CreateUserDto;
import ulitsa.raskolnikova.investshare.dto.auth.UserResponse;
import ulitsa.raskolnikova.investshare.entity.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "balance", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "favouredProjects", ignore = true)
    @Mapping(target = "projects", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserEntity toEntity(CreateUserDto dto);

    UserResponse toDto(UserEntity user);
}
