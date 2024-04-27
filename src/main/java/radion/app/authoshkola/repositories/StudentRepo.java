package radion.app.authoshkola.repositories;


import radion.app.authoshkola.entity.users.Student;

import java.util.List;

public interface StudentRepo{
    List<Student> findAll();
    Student findById(Long id);
    void save(Student student);
    void update(Student student);
    void delete(Long id);
}
