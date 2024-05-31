package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.dto.InstructorInfoDto;
import radion.app.authoshkola.model.dto.InstructorUpdateDto;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.users.User;

import java.util.List;

public interface InstructorService {
    List<User> findAll();
    User findById(Long id);
    InstructorInfoDto getUserInfo(String email);
    User findByInstructorForIdGroup(Long id_group);

    void save(User instructor);
    void update(InstructorUpdateDto instructor);
}
