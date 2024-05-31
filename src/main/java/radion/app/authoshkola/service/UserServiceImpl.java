package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.users.User;
import radion.app.authoshkola.repositories.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private ConnectionJDBC connectionJDBC;

    private final String getByEmail = """
            select * from users where users_email = ?;
            """;
    private final String getRoleByEmail = """
            select users.users_role from users where users_email = ? limit 1;
            """;

    @Override
    public Optional<User> findByEmail(String email) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getByEmail)){
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    User user = new User();
                    user.setId(resultSet.getLong(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setName(resultSet.getString(3));
                    user.setLastName(resultSet.getString(4));
                    user.setBirthday(resultSet.getDate(5));
                    user.setEmail(resultSet.getString(6));
                    user.setPassword(resultSet.getString(7));
                    user.setPhoneNumber(resultSet.getString(8));
                    user.setRole(resultSet.getString(9));
                    user.setIsStudying(resultSet.getBoolean(10));
                    return Optional.of(user);
                }else {
                    return Optional.empty();
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String findRoleByEmail(String email) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getRoleByEmail)){
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    return resultSet.getString(1);
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
