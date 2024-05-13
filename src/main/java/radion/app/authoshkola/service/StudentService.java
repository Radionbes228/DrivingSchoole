package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.StudentPreviewDto;
import radion.app.authoshkola.model.dto.StudentUpdateDto;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.users.Student;
import radion.app.authoshkola.repositories.StudentRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class StudentService implements StudentRepo {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from student order by name;
            """;
    private final String getUserInfo = """
            select student_id ,name , email, groups_id, phone_number, age from student where email = ?;
            """;
    private final String getById = """
            select * from student where student_id = ?;
            """;
    private final String getByEmail = """
            select * from student where email = ?;
            """;
    private final String saveStudent = """
            insert into student(name, email, password, phone_number, age, groups_id, roles, is_studying) values(?,?,?,?,?,?,?,?);
            """;
    private final String updateStudent = """
            update student 
                set name = ?, email = ?,phone_number = ?, age = ?, groups_id = ?,is_studying = ? 
                    where student_id = ?;
            """;
    private final String delete = """
            delete from student where student_id = ?;
            """;
    private final String getByStudentForGroupid = """
            select student_id, name, email, phone_number, age, is_studying from student where groups_id = ?;
            """;

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(getAll)){

            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setEmail(resultSet.getString(3));
                student.setPassword(resultSet.getString(4));
                student.setPhoneNumber(resultSet.getString(5));
                student.setAge(resultSet.getInt(6));
                student.setGroup_id(resultSet.getLong(7));
                student.setRole(resultSet.getString(8));
                student.setIsStudying(resultSet.getBoolean(9));

                studentList.add(student);
            }
            connection.close();
            return studentList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student findById(Long id) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getById)){
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getLong(1));
                    student.setName(resultSet.getString(2));
                    student.setEmail(resultSet.getString(3));
                    student.setPassword(resultSet.getString(4));
                    student.setPhoneNumber(resultSet.getString(5));
                    student.setAge(resultSet.getInt(6));
                    student.setGroup_id(resultSet.getLong(7));
                    student.setRole(resultSet.getString(8));
                    student.setIsStudying(resultSet.getBoolean(9));
                    return student;
                }else {
                    throw new RuntimeException("Error user");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getByEmail)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    Student student = new Student();
                    student.setId(resultSet.getLong(1));
                    student.setName(resultSet.getString(2));
                    student.setEmail(resultSet.getString(3));
                    student.setPassword(resultSet.getString(4));
                    student.setPhoneNumber(resultSet.getString(5));
                    student.setAge(resultSet.getInt(6));
                    student.setGroup_id(resultSet.getLong(7));
                    student.setRole(resultSet.getString(8));
                    student.setIsStudying(resultSet.getBoolean(9));
                    return Optional.of(student);
                }else {
                    return Optional.empty();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentInfoDto getUserInfo(String email) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getUserInfo)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    StudentInfoDto student = new StudentInfoDto();
                    student.setId(resultSet.getLong(1));
                    student.setName(resultSet.getString(2));
                    student.setEmail(resultSet.getString(3));
                    student.setGroupId(resultSet.getLong(4));
                    student.setPhoneNumber(resultSet.getString(5));
                    student.setAge(resultSet.getInt(6));
                    return student;
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(saveStudent)){
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setLong(6, student.getGroup_id());
            preparedStatement.setString(7, student.getRole());
            preparedStatement.setBoolean(8, student.getIsStudying());
            preparedStatement.setBoolean(8, student.getIsStudying());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(StudentUpdateDto student) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(updateStudent)){

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPhoneNumber());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setLong(5, student.getGroup_id());
            preparedStatement.setBoolean(6, student.getIsStudying());
            preparedStatement.setLong(7, student.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<StudentPreviewDto> findFindByStudentForGroupid(Long group_id) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getByStudentForGroupid)){

            preparedStatement.setLong(1, group_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                List<StudentPreviewDto> studentsPreviewDto = new ArrayList<>();
                while (resultSet.next()){
                    StudentPreviewDto studentPreviewDto = new StudentPreviewDto();
                    studentPreviewDto.setId(resultSet.getLong(1));
                    studentPreviewDto.setName(resultSet.getString(2));
                    studentPreviewDto.setEmail(resultSet.getString(3));
                    studentPreviewDto.setPhoneNumber(resultSet.getString(4));
                    studentPreviewDto.setAge(resultSet.getInt(5));
                    studentPreviewDto.setIsStudying(resultSet.getBoolean(6));

                    studentsPreviewDto.add(studentPreviewDto);
                }
                return studentsPreviewDto;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
