package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.schedule.DayOfWeek;
import radion.app.authoshkola.repositories.DayService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DayServiceImpl implements DayService {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from day_of_week;
            """;
    private final String getByid = """
            select * from day_of_week where day_id = ? limit 1;
            """;

    @Override
    public List<DayOfWeek> findAll() {
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement())   {

            try (ResultSet resultSet = statement.executeQuery(getAll)){
                List<DayOfWeek> days = new ArrayList<>();
                while (resultSet.next()){
                    DayOfWeek day = new DayOfWeek();
                    day.setId(resultSet.getLong(1));
                    day.setDayName(resultSet.getString(2));

                    days.add(day);
                }
                return days;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public DayOfWeek findById(Long idDay) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getByid))   {

            preparedStatement.setLong(1, idDay);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    DayOfWeek day = new DayOfWeek();
                    day.setId(resultSet.getLong(1));
                    day.setDayName(resultSet.getString(2));
                    return day;
                }else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
