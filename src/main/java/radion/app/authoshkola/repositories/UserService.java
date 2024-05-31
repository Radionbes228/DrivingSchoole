package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.users.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);

    String findRoleByEmail(String name);
}
