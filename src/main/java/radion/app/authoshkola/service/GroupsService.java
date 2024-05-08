package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.entity.schedule.Groups;
import radion.app.authoshkola.repositories.GroupsRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class GroupsService implements GroupsRepo {
    private ConnectionJDBC connectionJDBC;

    private final String countStudents = """
            select COUNT(*) as count from student where student.groups_id = ?;
            """;
    private final String getAll = """
            select * from groups;
            """;
    private final String getById = """
            select * from groups where group_id = ?;
            """;
    private final String save = """
            insert into groups(group_number, instructor_id) values(?,?);
            """;
    private final String delete = """
            delete from groups where group_id = ?;
            """;
    private final String update = """
            update groups set group_number = ?, instructor_id = ? where group_id = ?;
            """;


    @Override
    public List<Groups> findAll() {
        List<Groups> groupsList = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement()){

            try (ResultSet resultSet = statement.executeQuery(getAll)) {
                while (resultSet.next()) {
                    Groups groups = new Groups();
                    groups.setId(resultSet.getLong(1));
                    groups.setGroupNumber(resultSet.getInt(2));
                    groups.setInstructorId(resultSet.getLong(3));

                    groupsList.add(groups);
                }
                return groupsList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Groups findById(Long id) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getById)){

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();
                connection.close();

                Groups groups = new Groups();
                groups.setId(resultSet.getLong(1));
                groups.setGroupNumber(resultSet.getInt(2));
                groups.setInstructorId(resultSet.getLong(3));

                return groups;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Groups group) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(save)){

            preparedStatement.setLong(1, group.getGroupNumber());
            preparedStatement.setLong(2, group.getInstructorId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)){

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Groups group) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(update)){

            preparedStatement.setLong(1, group.getGroupNumber());
            preparedStatement.setLong(2, group.getInstructorId());
            preparedStatement.setLong(2, group.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer countStudent(Long id) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(countStudents)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    resultSet.next();
                    return resultSet.getInt("count");
                }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
