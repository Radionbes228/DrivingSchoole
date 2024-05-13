package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.dto.GroupInfoDto;
import radion.app.authoshkola.model.users.Instructors;

import java.util.List;
import java.util.Optional;

public interface InstructorRepo {
    List<Instructors> findAll();
    Instructors findById(Long id);
    void save(Instructors instructor);
    void update(Instructors instructor);
    void delete(Long id);

    Optional<Instructors> findByEmail(String email);
}
