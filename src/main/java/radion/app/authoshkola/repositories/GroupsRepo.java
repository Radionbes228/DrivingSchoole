package radion.app.authoshkola.repositories;

import radion.app.authoshkola.entity.schedule.Groups;

import java.util.List;

public interface GroupsRepo {
    List<Groups> findAll();
    Groups findById(Long id);
    void save(Groups group);
    void delete(Long id);
    void update(Groups group);
}
