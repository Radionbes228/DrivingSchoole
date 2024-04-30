package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.entity.users.Student;
import radion.app.authoshkola.repositories.StudentRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentService implements StudentRepo {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from student order by name;
            """;
    private final String getById = """
            select * from student where student_id = ?;
            """;
    private final String saveStudent = """
            insert into student(name, email, password, phone_number, age, groups_id, roles, is_studying) values(?,?,?,?,?,?,?,?);
            """;
    private final String updateStudent = """
            update student 
                set name = ?, email = ?, password = ?, phone_number = ?, age = ?, groups_id = ?, roles = ?, is_studying = ? 
                    where student_id = ?;
            """;
    private final String delete = """
            delete from student where student_id = ?;
            """;

    @Override
    public List<Student> findAll() {
        List<Student> studentList = new ArrayList<>();
        Connection connection = connectionJDBC.connect();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getLong(1));
                student.setName(resultSet.getString(2));
                student.setEmail(resultSet.getString(3));
                student.setPassword(resultSet.getString(4));
                student.setPhoneNumber(resultSet.getString(5));
                student.setAge(resultSet.getInt(6));
                student.setGroup_id(resultSet.getLong(7));
                student.setRoles(resultSet.getString(8));
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
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.close();

            Student student = new Student();
            student.setId(resultSet.getLong(1));
            student.setName(resultSet.getString(2));
            student.setEmail(resultSet.getString(3));
            student.setPassword(resultSet.getString(4));
            student.setPhoneNumber(resultSet.getString(5));
            student.setAge(resultSet.getInt(6));
            student.setGroup_id(resultSet.getLong(7));
            student.setRoles(resultSet.getString(8));
            student.setIsStudying(resultSet.getBoolean(9));

            return student;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Student student) {
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(saveStudent);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setLong(6, student.getGroup_id());
            preparedStatement.setString(7, student.getRoles());
            preparedStatement.setBoolean(8, student.getIsStudying());
            preparedStatement.setBoolean(8, student.getIsStudying());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(updateStudent);
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getPassword());
            preparedStatement.setString(4, student.getPhoneNumber());
            preparedStatement.setInt(5, student.getAge());
            preparedStatement.setLong(6, student.getGroup_id());
            preparedStatement.setString(7, student.getRoles());
            preparedStatement.setBoolean(8, student.getIsStudying());
            preparedStatement.setLong(9, student.getId());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
