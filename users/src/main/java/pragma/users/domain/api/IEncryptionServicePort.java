package pragma.users.domain.api;

public interface IEncryptionServicePort {
    String encode (String password);
    boolean verify (String password, String encodedPassword);
}
