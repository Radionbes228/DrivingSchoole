package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.entity.schedule.Schedule;
import radion.app.authoshkola.repositories.ScheduleRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ScheduleService implements ScheduleRepo {
    private ConnectionJDBC connectionJDBC;

    private final String getAll = """
            select * from schedule order by year, start_week;
            """;
    private final String getByYearAndStartWeek = """
            select * from schedule where year = ? AND start_week = ?;
            """;
    private final String getById = """
            select * from schedule where schedule_id = ?;
            """;
    private final String saveSchedule = """
            insert into schedule(year, start_week, end_week) values(?,?,?);
            """;

    @Override
    public List<Schedule> findAll() {
        List<Schedule> scheduleList = new ArrayList<>();
        try {
            Connection connect = connectionJDBC.connect();
            Statement statement = connect.createStatement();
            ResultSet resultSet = statement.executeQuery(getAll);
            while (resultSet.next()){
                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong(1));
                schedule.setYear(resultSet.getString(2));
                schedule.setStartWeek(resultSet.getString(3));
                schedule.setEndWeek(resultSet.getString(4));

                scheduleList.add(schedule);
            }
            connect.close();
            return scheduleList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Schedule findByYearAndStartWeek(Integer year, Date date) {
        try {
            Connection connect = connectionJDBC.connect();
            PreparedStatement preparedStatement = connect.prepareStatement(getByYearAndStartWeek);
            preparedStatement.setInt(1, year);
            preparedStatement.setDate(2, (java.sql.Date) date);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Schedule schedule = new Schedule();
            schedule.setId(resultSet.getLong(1));
            schedule.setYear(resultSet.getString(2));
            schedule.setStartWeek(resultSet.getString(3));
            schedule.setEndWeek(resultSet.getString(4));

            connect.close();
            return schedule;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Schedule findById(Long id) {
        try {
            Connection connect = connectionJDBC.connect();
            PreparedStatement preparedStatement = connect.prepareStatement(getById);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Schedule schedule = new Schedule();
            schedule.setId(resultSet.getLong(1));
            schedule.setYear(resultSet.getString(2));
            schedule.setStartWeek(resultSet.getString(3));
            schedule.setEndWeek(resultSet.getString(4));

            connect.close();
            return schedule;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Schedule schedule) {
        try {
            Connection connection = connectionJDBC.connect();
            PreparedStatement preparedStatement = connection.prepareStatement(saveSchedule);
            preparedStatement.setString(1, schedule.getYear());
            preparedStatement.setString(2, schedule.getStartWeek());
            preparedStatement.setString(3, schedule.getEndWeek());
            preparedStatement.execute();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
