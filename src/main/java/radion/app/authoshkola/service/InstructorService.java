package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.entity.users.Instructors;
import radion.app.authoshkola.repositories.InstructorRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class InstructorService implements InstructorRepo {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from instructors order by name;
            """;
    private final String getById = """
            select * from instructors where instructor_id = ?;
            """;
    private final String save = """
            insert into instructors(name, email, password, age, phone_number, role) values(?,?,?,?,?,?);
            """;
    private final String update = """
            update instructors set name = ?, email = ?, password = ?, age = ?, phone_number = ?, role = ? where instructor_id = ?
            """;
    private final String delete = """
            delete from instructors where instructor_id = ?;
            """;

    @Override
    public List<Instructors> findAll() {
        List<Instructors> instructorsList = new ArrayList<>();
        try {
            Connection connect = connectionJDBC.connect();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()){
                Instructors instructors = new Instructors();
                instructors.setId(resultSet.getLong(1));
                instructors.setName(resultSet.getString(2));
                instructors.setEmail(resultSet.getString(3));
                instructors.setPassword(resultSet.getString(4));
                instructors.setAge(resultSet.getInt(5));
                instructors.setPhoneNumber(resultSet.getString(6));
                instructors.setRole(resultSet.getString(7));

                instructorsList.add(instructors);
            }
            connect.close();
            return instructorsList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Instructors findById(Long id) {
        try {
            Connection connect = connectionJDBC.connect();
            PreparedStatement preparedStatement = connect.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            connect.close();

            resultSet.next();
            Instructors instructors = new Instructors();
            instructors.setId(resultSet.getLong(1));
            instructors.setName(resultSet.getString(2));
            instructors.setEmail(resultSet.getString(3));
            instructors.setPassword(resultSet.getString(4));
            instructors.setAge(resultSet.getInt(5));
            instructors.setPhoneNumber(resultSet.getString(6));
            instructors.setRole(resultSet.getString(7));

            return instructors;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Instructors instructor) {
        try {
            Connection connect = connectionJDBC.connect();
            PreparedStatement preparedStatement = connect.prepareStatement(save);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setString(2, instructor.getEmail());
            preparedStatement.setString(3, instructor.getPassword());
            preparedStatement.setInt(4, instructor.getAge());
            preparedStatement.setString(5, instructor.getPhoneNumber());
            preparedStatement.setString(6, instructor.getRole());
            preparedStatement.execute();
            connect.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Instructors instructor) {
        try {
            Connection connect = connectionJDBC.connect();
            PreparedStatement preparedStatement = connect.prepareStatement(update);
            preparedStatement.setString(1, instructor.getName());
            preparedStatement.setString(2, instructor.getEmail());
            preparedStatement.setString(3, instructor.getPassword());
            preparedStatement.setInt(4, instructor.getAge());
            preparedStatement.setString(5, instructor.getPhoneNumber());
            preparedStatement.setString(6, instructor.getRole());
            preparedStatement.setLong(7, instructor.getId());
            preparedStatement.execute();
            connect.close();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
