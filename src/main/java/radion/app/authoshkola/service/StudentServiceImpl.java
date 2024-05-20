package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.UserUpdateDto;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.roles.RolesEnum;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.model.users.User;
import radion.app.authoshkola.repositories.GroupService;
import radion.app.authoshkola.repositories.StudentService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from users where users_role = ? order by users_first_name;
            """;
    private final String getAllByGroup = """
            select users.*, ug.fk_group_id from users join users_group ug on ug.fk_user_id = users.users_id where ug.fk_group_id = ?;
            """;
    private final String getById = """
            select * from users where users_id = ?;
            """;
    private final String getByEmail = """
            select * from users where users_email = ?;
            """;
    private final String saveStudent = """
            insert into users(users_first_name, users_name, users_last_name, users_date_of_birthday, users_email, users_password, users_phone_number, users_role, users_is_studying)
                values(?,?,?,?,?,?,?,?,?);
            """;
    private final String updateStudent = """
            update users 
                set users_first_name = ?, users_name = ?, users_last_name = ?, users_date_of_birthday = ?, users_email = ?, users_phone_number = ?, users_is_studying = ?
                    where users_id = ?;
            """;
    private final String delete = """
            delete from users where users_id = ?;
            """;
    private final String getGroupNumberForStudentEmail = """
            select "group".* from "group"
                join users_group on "group".group_id = users_group.fk_group_id
                    where users_group.fk_user_id = (
                        select users_id from users where users_email = ? limit 1
                    );
            """;

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement statement = connection.prepareStatement(getAll)){

            statement.setString(1, String.valueOf(RolesEnum.ROLE_STUDENT));
            return getUsers(userList, statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findAllByGroup(Long group_id) {
        List<User> userList = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement statement = connection.prepareStatement(getAllByGroup)){

            statement.setLong(1, group_id);
            return getUsers(userList, statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<User> getUsers(List<User> userList, PreparedStatement statement) throws SQLException {
        try (ResultSet resultSet = statement.executeQuery()){
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setFirstName(resultSet.getString(2));
                user.setName(resultSet.getString(3));
                user.setLastName(resultSet.getString(4));
                user.setBirthday(resultSet.getDate(5));
                user.setEmail(resultSet.getString(6));
                user.setPhoneNumber(resultSet.getString(8));
                user.setIsStudying(resultSet.getBoolean(10));

                userList.add(user);
            }
            return userList;
        }
    }

    @Override
    public User findById(Long id) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getById)){
            preparedStatement.setLong(1, id);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setName(resultSet.getString(3));
                    user.setLastName(resultSet.getString(4));
                    user.setBirthday(resultSet.getDate(5));
                    user.setEmail(resultSet.getString(6));
                    user.setPhoneNumber(resultSet.getString(8));
                    user.setIsStudying(resultSet.getBoolean(10));

                    return user;
                }else {
                    throw new NoSuchElementException(String.format("User not found by id = %s", id));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByEmail(String email) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getByEmail)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setName(resultSet.getString(2));
                    user.setEmail(resultSet.getString(3));
                    user.setPassword(resultSet.getString(4));
                    user.setPhoneNumber(resultSet.getString(5));
                    user.setIsStudying(resultSet.getBoolean(9));
                    return user;
                }else {
                    throw new NoSuchElementException(String.format("User not found by email = %s", email));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public StudentInfoDto getUserInfo(String email) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getByEmail)) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()){
                    try {
                        StudentInfoDto student = new StudentInfoDto();
                        Groups groups = findGroupForStudentEmail(email);
                        student.setId(resultSet.getLong(1));
                        student.setFirstName(resultSet.getString(2));
                        student.setName(resultSet.getString(3));
                        student.setLastName(resultSet.getString(4));
                        student.setBirthday(resultSet.getDate(5));
                        student.setEmail(resultSet.getString(6));
                        student.setPhoneNumber(resultSet.getString(8));

                        groups.setId(groups.getId());
                        groups.setGroupNumber(groups.getGroupNumber());

                        student.setGroup(groups);
                        student.setRole(resultSet.getString(9));
                        student.setIsStudying(resultSet.getBoolean(10));
                        return student;
                    }catch (NoSuchElementException e){
                        return null;
                    }
                }else {
                    throw new NoSuchElementException(String.format("User not found by email = %s", email));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Groups findGroupForStudentEmail(String email) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getGroupNumberForStudentEmail)){

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    Groups group = new Groups();
                    group.setId(resultSet.getLong(1));
                    group.setGroupNumber(resultSet.getInt(2));
                    group.setInstructorId(resultSet.getLong(3));

                    return group;
                }else {
                    throw new NoSuchElementException("Not find group number");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    @Override
    public void update(UserUpdateDto student) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(updateStudent)){

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setDate(4, student.getBirthday());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPhoneNumber());
            preparedStatement.setBoolean(7, student.getIsStudying());

            preparedStatement.setLong(8, student.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException("Update is bad request!");
        }
    }

    @SneakyThrows
    @Override
    public void delete(Long id) {
        try(Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(delete)) {

            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException("Delete is bad request!");
        }
    }

    @SneakyThrows
    @Override
    public void save(User student) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(saveStudent)){

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getLastName());
            preparedStatement.setDate(4, student.getBirthday());
            preparedStatement.setString(5, student.getEmail());
            preparedStatement.setString(6, student.getPassword());
            preparedStatement.setString(7, student.getPhoneNumber());
            preparedStatement.setString(8, student.getRole());
            preparedStatement.setBoolean(9, student.getIsStudying());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new SQLException("Save is bad request!");
        }
    }
}
