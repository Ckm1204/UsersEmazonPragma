package pragma.users.infraestructure.configuration.openApiSwagger;

public class ConstantsOpenApi {


    public static final String CODE_STATUS_201 = "201";
    public static final String CODE_STATUS_400 = "400";
    public static final String CODE_STATUS_403 = "403";
    public static final String CODE_STATUS_409 = "409";

    public static final String DESCRIPTION_STATUS_201 = "User created";
    public static final String DESCRIPTION_STATUS_400 = "Bad request";
    public static final String DESCRIPTION_STATUS_403 = "Forbidden";
    public static final String DESCRIPTION_STATUS_409 = "User already exists";

    public static final String AUTHENTICATION_CONTROLLER_TAG = "Authentication Controller";
    public static final String AUTHENTICATION_CONTROLLER_DESCRIPTION = "Endpoints for user authentication";
    public static final String AUTHENTICATION_CONTROLLER_OPERATION_DESCRIPTION = "Authenticate a user";

    public static final String USER_CONTROLLER_TAG = "User Controller";
    public static final String USER_CONTROLLER_DESCRIPTION = "Endpoints for user management";
    public static final String CREATE_WAREHOUSE_ASSISTANT_USER_OPERATION_DESCIPTION = "Create a warehouse assistant user";
    public static final String CREATE_CLIENT_USER_OPERATION_DESCIPTION = "Create a client user";



    private ConstantsOpenApi(){
    }
}
