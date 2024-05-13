package radion.app.authoshkola.repositories;


import radion.app.authoshkola.model.dto.StudentPreviewDto;
import radion.app.authoshkola.model.dto.StudentUpdateDto;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.users.Student;

import java.util.List;
import java.util.Optional;

public interface StudentRepo{
    List<Student> findAll();
    Student findById(Long id);
    Optional<Student> findByEmail(String email);
    StudentInfoDto getUserInfo(String email);
    void save(Student student);
    void update(StudentUpdateDto student);
    void delete(Long id);
    List<StudentPreviewDto> findFindByStudentForGroupid(Long group_id);
}
