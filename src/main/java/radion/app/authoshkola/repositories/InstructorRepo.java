package radion.app.authoshkola.repositories;

import radion.app.authoshkola.entity.users.Instructors;

import java.util.List;

public interface InstructorRepo {
    List<Instructors> findAll();
    Instructors findById(Long id);
    void save(Instructors instructor);
    void update(Instructors instructor);
    void delete(Long id);
}
