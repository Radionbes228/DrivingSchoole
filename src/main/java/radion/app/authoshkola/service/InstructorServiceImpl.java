package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.InstructorInfoDto;
import radion.app.authoshkola.model.dto.InstructorUpdateDto;
import radion.app.authoshkola.model.roles.RolesEnum;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.model.users.User;
import radion.app.authoshkola.repositories.InstructorService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

//TODO
@Controller
@AllArgsConstructor
public class InstructorServiceImpl implements InstructorService {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from users where users.users_role = ? order by users_first_name;
            """;
    private final String getByEmail = """
            select * from users where users_email = ?;
            """;
    private final String getById = """
            select * from users where users_id = ?;
            """;
    private final String getInstructorBygroupId = """
            select users.* from users
                join "group" g on users.users_id = g.group_of_instructor_id
                    where group_id = ?;
            """;
    private final String save = """
            insert into users(users_first_name, users_name, users_last_name, users_date_of_birthday, users_email, users_password, users_phone_number, users_role)
                values(?,?,?,?,?,?,?,?);
            """;
    private final String update = """
            update users 
                set users_first_name = ?, users_name = ?, users_last_name = ?, users_date_of_birthday = ?, users_email = ?, users_phone_number = ?
                    where users_id = ?;
            """;
    private final String delete = """
            delete from users where users_id = ?;
            """;
    private final String getGroupsForInstrutorEmail = """
            select "group".* from "group"
                join users on "group".group_of_instructor_id = users.users_id where users.users_id = (
                    select users_id from users where users_email = ? 
                );
            """;

    @Override
    public List<User> findAll() {
        List<User> instructorsList = new ArrayList<>();
        try (Connection connect = connectionJDBC.connect();
             PreparedStatement statement = connect.prepareStatement(getAll)) {

            statement.setString(1, String.valueOf(RolesEnum.ROLE_INSTRUCTOR));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User instructors = new User();
                    instructors.setId(resultSet.getLong(1));
                    instructors.setFirstName(resultSet.getString(2));
                    instructors.setName(resultSet.getString(3));
                    instructors.setLastName(resultSet.getString(4));
                    instructors.setBirthday(resultSet.getDate(5));
                    instructors.setEmail(resultSet.getString(6));
                    instructors.setPhoneNumber(resultSet.getString(8));

                    instructorsList.add(instructors);
                }
                return instructorsList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public User findById(Long id) {
        try (Connection connect = connectionJDBC.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(getById)) {

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
               if (resultSet.next()) {
                   User instructors = new User();
                   instructors.setId(resultSet.getLong(1));
                   instructors.setFirstName(resultSet.getString(2));
                   instructors.setName(resultSet.getString(3));
                   instructors.setLastName(resultSet.getString(4));
                   instructors.setBirthday(resultSet.getDate(5));
                   instructors.setEmail(resultSet.getString(6));
                   instructors.setPhoneNumber(resultSet.getString(8));

                   return instructors;
               }else {
                   throw new NoSuchElementException(String.format("Instructor not find by id = %s", id));
               }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public InstructorInfoDto getUserInfo(String email) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getByEmail)){

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    InstructorInfoDto instructor = new InstructorInfoDto();
                    instructor.setId(resultSet.getLong(1));
                    instructor.setFirstName(resultSet.getString(2));
                    instructor.setName(resultSet.getString(3));
                    instructor.setLastName(resultSet.getString(4));
                    instructor.setBirthday(resultSet.getDate(5));
                    instructor.setEmail(resultSet.getString(6));
                    instructor.setPhoneNumber(resultSet.getString(8));

                    instructor.setGroups(findGroupsForInstructorEmail(email));
                    instructor.setRole(resultSet.getString(9));

                    return instructor;
                }else {
                    throw new NoSuchElementException(String.format("Instructor not find by email = %s", email));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Groups> findGroupsForInstructorEmail(String email) {
        List<Groups> groups = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getGroupsForInstrutorEmail)){

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    do {
                        Groups group = new Groups();
                        group.setId(resultSet.getLong(1));
                        group.setGroupNumber(resultSet.getInt(2));
                        group.setInstructorId(resultSet.getLong(3));
                        groups.add(group);
                    }while (resultSet.next());
                    return groups;
                }else {
                    throw new NoSuchElementException("Not find group number");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByInstructorForIdGroup(Long id_group) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getInstructorBygroupId)) {

            preparedStatement.setLong(1, id_group);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    User instructors = new User();
                    instructors.setId(resultSet.getLong(1));
                    instructors.setFirstName(resultSet.getString(2));
                    instructors.setName(resultSet.getString(3));
                    instructors.setLastName(resultSet.getString(4));
                    instructors.setBirthday(resultSet.getDate(5));
                    instructors.setEmail(resultSet.getString(6));
                    instructors.setPhoneNumber(resultSet.getString(8));

                    return instructors;
                }else {
                    throw new NoSuchElementException(String.format("Not find user by group_id = %s", id_group));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(User instructor) {
        try (Connection connect = connectionJDBC.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(save)){

            preparedStatement.setString(1, instructor.getFirstName());
            preparedStatement.setString(2, instructor.getName());
            preparedStatement.setString(3, instructor.getLastName());
            preparedStatement.setDate(4, instructor.getBirthday());
            preparedStatement.setString(5, instructor.getEmail());
            preparedStatement.setString(6, instructor.getPassword());
            preparedStatement.setString(7, instructor.getPhoneNumber());
            preparedStatement.setString(8, instructor.getRole());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(InstructorUpdateDto instructor) {
        try (Connection connect = connectionJDBC.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(update)){

            preparedStatement.setString(1, instructor.getFirstName());
            preparedStatement.setString(2, instructor.getName());
            preparedStatement.setString(3, instructor.getLastName());
            preparedStatement.setDate(4, instructor.getBirthday());
            preparedStatement.setString(5, instructor.getEmail());
            preparedStatement.setString(6, instructor.getPhoneNumber());
            preparedStatement.setLong(7, instructor.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
