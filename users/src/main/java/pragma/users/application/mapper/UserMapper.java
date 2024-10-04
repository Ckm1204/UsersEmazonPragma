package pragma.users.application.mapper;

import org.mapstruct.Mapping;
import pragma.users.application.dto.request.UserRequestDTO;
import pragma.users.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mapping(target = "role", expression = "java(1)")
    UserModel toUserModelAdmin(UserRequestDTO userRequestDTO);

    @Mapping(target = "role", expression = "java(2)")
    UserModel toUserModelAuxBodega(UserRequestDTO userRequestDTO);

    @Mapping(target = "role", expression = "java(3)")
    UserModel toUSer(UserRequestDTO userRequestDTO);
}
