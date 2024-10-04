package pragma.users.application.service;

import pragma.users.application.dto.request.UserRequestDTO;


public interface IUserService {

    void createUserAdmin(UserRequestDTO userRequestDTO);


    void createUserAuxBodega(UserRequestDTO userRequestDTO);



    void createUser(UserRequestDTO userRequestDTO);

}
