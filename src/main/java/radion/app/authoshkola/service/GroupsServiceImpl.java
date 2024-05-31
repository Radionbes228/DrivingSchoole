package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.repositories.GroupService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class GroupsServiceImpl implements GroupService {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from "group";
            """;
    private final String getById = """
            select * from "group" where group_id = ?;
            """;
    private final String save = """
            insert into "group"(group_number, group_of_instructor_id) values(?,?);
            """;
    private final String delete = """
            update public.users_group set fk_group_id = null
                where fk_group_id = ?;
                    delete from "group" where "group".group_id = ?;
            """;
    private final String update = """
            update "group" set group_number = ?, group_of_instructor_id = ? where group_id = ?;
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
                if (resultSet.next()) {

                    Groups groups = new Groups();
                    groups.setId(resultSet.getLong(1));
                    groups.setGroupNumber(resultSet.getInt(2));
                    groups.setInstructorId(resultSet.getLong(3));

                    return groups;
                }else {
                    return null;
                }
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
            throw new RuntimeException("Группа с данным номером уже есть");
        }
    }

    @Override
    public void delete(Long idGroup) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(delete)){

            preparedStatement.setLong(1, idGroup);
            preparedStatement.setLong(2, idGroup);
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
            preparedStatement.setLong(3, group.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
