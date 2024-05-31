package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.schedule.Time;
import radion.app.authoshkola.repositories.TimeService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
@AllArgsConstructor
public class TimeServiceImpl implements TimeService {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from time order by time;
            """;
    private final String getAllNot = """
            SELECT t.* FROM time t
            WHERE t.time_id NOT IN (
                SELECT r.fk_time_of_record FROM public.record r
                                                    JOIN public.users_group ug ON ug.fk_user_id = r.fk_student_id
                                                    JOIN "group" g ON g.group_id = ug.fk_group_id
                                                    JOIN day_of_week dow ON r.fk_day_of_week = dow.day_id
                                                    JOIN public.week w ON w.week_id = r.fk_week_id
                WHERE w.week_id = ? AND dow.day_id = ? AND g.group_id = ?
            );
            """;

    @Override
    public List<Time> findAll() {
        List<Time> times = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement()) {

            try (ResultSet resultSet = statement.executeQuery(getAll)){
                if (resultSet.next()){
                    do {
                        Time time = new Time();
                        time.setId(resultSet.getLong(1));
                        time.setTime(resultSet.getTime(2));

                        times.add(time);
                    }while (resultSet.next());
                }else {
                    throw new NoSuchElementException("Time is not find!");
                }
            }
            return times;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Time> findAllByGroupWeekDay(Long week_id, Long id_day, Long group_id) {
        List<Time> times = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllNot)){

            preparedStatement.setLong(1, week_id);
            preparedStatement.setLong(2, id_day);
            preparedStatement.setLong(3, group_id);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    do {
                        Time time = new Time();
                        time.setId(resultSet.getLong(1));
                        time.setTime(resultSet.getTime(2));

                        times.add(time);
                    }while (resultSet.next());
                }else {
                    return findAll();
                }
            }
            return times;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
