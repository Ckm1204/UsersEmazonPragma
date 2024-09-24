package pragma.users.infraestructure.out.jpa.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pragma.users.domain.model.request.UserModelRequest;
import pragma.users.infraestructure.out.jpa.entity.User;



@Mapper(componentModel = "spring")
public interface UserMapperJPA {
    @Mapping(source = "role", target = "role.id")
    User toUser(UserModelRequest userModelRequest);
}
