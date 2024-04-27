package radion.app.authoshkola.repositories;

import radion.app.authoshkola.entity.users.Admin;

import java.util.List;

public interface AdminRepo {
    List<Admin> findAll();
    Admin findById();
    void save(Admin instructor);
    void update(Admin instructor);
    void delete(Long id);
}
