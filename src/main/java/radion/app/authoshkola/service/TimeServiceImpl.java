package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
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
            select time.* from time
                join public.record r on time.time_id <> r.fk_time_of_record
                join public.week w on w.week_id = r.fk_week_id
                join public.day_of_week dow on dow.day_id = r.fk_day_of_week
                join public.users u on u.users_id = r.fk_student_id
                join public.users_group ug on u.users_id = ug.fk_user_id
                join public."group" g on g.group_id = ug.fk_group_id
            where w.week_id = ? and dow.day_id = ? and g.group_id = ?;
            """;
    private final String getByTime = """
            select * from time where time = ?;
            """;
    private final String save = """
            insert into time(time) values(?);
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

    @Override
    public Time findByTime(Time time) {
        return null;
    }

    @Override
    public Time findById(Long id) {
        return null;
    }

    @Override
    public void save(Time time) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Integer getYear() {
        return null;
    }

/*
    @Override
    public List<Time> findAll() {
        List<Time> timeList = new ArrayList<>();
        try {
            Connection connection = connectionJDBC.connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);

            while (resultSet.next()){
                Time time = new Time();
                time.setId(resultSet.getLong(1));
                time.setTime(resultSet.getTime(2));

                timeList.add(time);
            }

            connection.close();
            return timeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Time findByTime(Time time) {
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(getByTime);
            preparedStatement.setString(1, time.getTime());
            ResultSet resultSet = preparedStatement.executeQuery();
            connection.close();

            Time returnTime = new Time();
            returnTime.setId(resultSet.getLong(1));
            returnTime.setTime(resultSet.getString(2));

            return returnTime;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Time findById(Long id) {
        return null;
    }

    @Override
    public void save(Time time) {
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setString(1, time.getTime());
            preparedStatement.executeQuery();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {}

    @Override
    public Integer getYear(){
        return Integer.valueOf(String.valueOf(Year.now()));
    }*/
}
