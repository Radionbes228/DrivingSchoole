package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.ScheduleDto;
import radion.app.authoshkola.model.schedule.Schedule;
import radion.app.authoshkola.repositories.ScheduleRepo;

import java.sql.*;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

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
        saveAndSchedule();

        List<Schedule> scheduleList = new ArrayList<>();
        try (Connection connect = connectionJDBC.connect();
             Statement statement = connect.createStatement()){

            try (ResultSet resultSet = statement.executeQuery(getAll)) {
                while (resultSet.next()) {
                    Schedule schedule = new Schedule();
                    schedule.setId(resultSet.getLong(1));
                    schedule.setYear(resultSet.getString(2));
                    schedule.setStartWeek(resultSet.getDate(3));
                    schedule.setEndWeek(resultSet.getDate(4));

                    scheduleList.add(schedule);
                }
                return scheduleList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Schedule findByYearAndStartWeek(Integer year, Date date) {
        try (Connection connect = connectionJDBC.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(getByYearAndStartWeek)){

            preparedStatement.setInt(1, year);
            preparedStatement.setDate(2, date);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();

                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong(1));
                schedule.setYear(resultSet.getString(2));
                schedule.setStartWeek(resultSet.getDate(3));
                schedule.setEndWeek(resultSet.getDate(4));
                return schedule;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Schedule findById(Long id) {
        try (Connection connect = connectionJDBC.connect();
             PreparedStatement preparedStatement = connect.prepareStatement(getById)){

            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                resultSet.next();

                Schedule schedule = new Schedule();
                schedule.setId(resultSet.getLong(1));
                schedule.setYear(resultSet.getString(2));
                schedule.setStartWeek(resultSet.getDate(3));
                schedule.setEndWeek(resultSet.getDate(4));
                return schedule;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(Schedule schedule) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(saveSchedule)){

            preparedStatement.setString(1, schedule.getYear());
            preparedStatement.setDate(2, schedule.getStartWeek());
            preparedStatement.setDate(3, schedule.getEndWeek());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void saveAndSchedule(){
        int year = LocalDate.now().getYear();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        List<ScheduleDto> schedule = new ArrayList<>();

        LocalDate firstMonday = startOfYear.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (firstMonday.isBefore(startOfYear)) {
            firstMonday = firstMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        while (firstMonday.isBefore(endOfYear)) {
            LocalDate endOfWeek = firstMonday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

            ScheduleDto scheduleDto = new ScheduleDto();
            scheduleDto.setYear(String.valueOf(year));
            scheduleDto.setStartWeek(Date.valueOf(firstMonday));
            scheduleDto.setEndWeek(Date.valueOf(endOfWeek));
            schedule.add(scheduleDto);

            firstMonday = endOfWeek.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        try (Connection connect =  connectionJDBC.connect();
             Statement statement = connect.createStatement()){

            try (PreparedStatement preparedStatement = connect.prepareStatement(saveSchedule);
                 ResultSet resultSet = statement.executeQuery(getAll)) {
                if (resultSet.next()) {
                    do{
                        for (ScheduleDto scheduleDto : schedule) {
                            if (resultSet.getString(2).equals(String.valueOf(year))){
                                continue;
                            }else {
                                preparedStatement.setString(1, String.valueOf(year));
                                preparedStatement.setDate(2, scheduleDto.getStartWeek());
                                preparedStatement.setDate(3, scheduleDto.getEndWeek());
                                preparedStatement.execute();
                            }
                        }

                    } while (resultSet.next());
                }else {
                    for (ScheduleDto scheduleDto : schedule) {
                        preparedStatement.setString(1, String.valueOf(year));
                        preparedStatement.setDate(2, scheduleDto.getStartWeek());
                        preparedStatement.setDate(3, scheduleDto.getEndWeek());
                        preparedStatement.execute();
                    }
                }


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
