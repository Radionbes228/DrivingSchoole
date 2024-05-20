package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.users.User;
import java.util.List;

public interface AdminService {
    List<User> findAll();
    User findById();
    void save(User instructor);
    void update(User instructor);
    void delete(Long id);
}
