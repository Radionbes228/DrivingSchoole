package radion.app.authoshkola.service;

import jdk.jfr.consumer.RecordingStream;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.schedule.DayOfWeek;
import radion.app.authoshkola.repositories.DayRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DayService implements DayRepo {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from day_of_week;
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
}
