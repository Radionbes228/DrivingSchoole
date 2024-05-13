package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.schedule.Time;
import radion.app.authoshkola.repositories.TimeRepo;

import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class TimeService implements TimeRepo {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from time order by time;
            """;
    private final String getByTime = """
            select * from time where time = ?;
            """;
    private final String save = """
            insert into time(time) values(?);
            """;


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
                time.setTime(resultSet.getString(2));

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
    }
}
