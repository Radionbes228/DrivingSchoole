package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.AdminDto;
import radion.app.authoshkola.model.dto.InstructorInfoDto;
import radion.app.authoshkola.model.users.User;
import radion.app.authoshkola.repositories.AdminService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {
    private ConnectionJDBC connectionJDBC;

    private final String getByid = """
            select * from users where users_email = ? limit 1;
            """;

    @Override
    public AdminDto findByEmail(String email) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getByid)){

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){

                    AdminDto user = new AdminDto();
                    user.setId(resultSet.getLong(1));
                    user.setFirstName(resultSet.getString(2));
                    user.setName(resultSet.getString(3));
                    user.setLastName(resultSet.getString(4));
                    user.setBirthday(resultSet.getDate(5));
                    user.setEmail(resultSet.getString(6));
                    user.setPhoneNumber(resultSet.getString(8));
                    user.setRole(resultSet.getString(9));

                    return user;
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
