package africa.semicolon.sendAm.data.repositories;
import africa.semicolon.sendAm.data.model.User;
import java.util.List;

public interface UserRepository {
    User save(User user, String email);
    User findByFullName(String fullName);
    User findByEmail(String email);
    void delete(User user);
    void delete(String email);
    List<User> findAll();

    int count();
}
