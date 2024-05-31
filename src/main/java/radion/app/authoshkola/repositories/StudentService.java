package radion.app.authoshkola.repositories;


import radion.app.authoshkola.model.dto.UserPreviewDto;
import radion.app.authoshkola.model.dto.UserUpdateDto;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.users.User;

import java.util.List;

public interface StudentService {
    List<User> findAll();
    List<User> findAllByGroup(Long group_id);
    User findById(Long id);
    UserUpdateDto findByIdUpdate(Long id);
    List<UserPreviewDto> findAllPreview();
    StudentInfoDto getUserInfo(String email);

    void save(User student, Long groupId);
    void update(UserUpdateDto student);
}
