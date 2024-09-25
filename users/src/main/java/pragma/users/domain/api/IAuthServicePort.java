package pragma.users.domain.api;

public interface IAuthServicePort {
    String login(String name, String password);
}
