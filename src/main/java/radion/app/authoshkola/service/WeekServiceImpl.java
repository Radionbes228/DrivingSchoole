package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.schedule.Week;
import radion.app.authoshkola.repositories.WeekService;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class WeekServiceImpl implements WeekService {
    private ConnectionJDBC connectionJDBC;


    private final String getAll = """
            select * from week order by start_date_of_week;
            """;
    private final String getById = """
            select * from week where week_id = ? limit 1;
            """;
    private final String saveWeek = """
            insert into week(start_date_of_week, last_date_of_week) values(?,?);
            """;
    private final String getFirstWeek = """
            select week.week_id from week order by start_date_of_week limit 1;
            """;

    @Override
    public List<Week> findAllWeek() {
        List<Week> weekList = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(getAll)){
                if (resultSet.next()){
                    do {
                        Week week = new Week();
                        week.setId(resultSet.getLong(1));
                        week.setStartDateOfWeek(resultSet.getDate(2));
                        week.setLastDateOfWeek(resultSet.getDate(3));
                        weekList.add(week);
                    }while (resultSet.next());
                }
            }
            return weekList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long findFirstWeek() {
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(getFirstWeek)){
                if (resultSet.next()){
                    return resultSet.getLong(1);
                }else {
                    return null;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save() {
        List<Week> weeks = getWeeks();
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement())   {

            try (ResultSet resultSet = statement.executeQuery(getAll)){
                if (resultSet.next()){

                    do {

                        for (Week week: weeks) {
                            if (resultSet.getDate(2).getYear() == week.getStartDateOfWeek().getYear()){
                                continue;
                            }else {
                                try (PreparedStatement preparedStatement = connection.prepareStatement(saveWeek)) {

                                    preparedStatement.setDate(1, week.getStartDateOfWeek());
                                    preparedStatement.setDate(2, week.getLastDateOfWeek());
                                    preparedStatement.execute();

                                }catch (SQLException e){
                                    throw new SQLException(String.format("Not save week = %s", week));
                                }
                            }

                        }
                    } while (resultSet.next());
                }else {
                    try (PreparedStatement preparedStatement = connection.prepareStatement(saveWeek)){
                        for (Week week: weeks) {
                            preparedStatement.setDate(1, week.getStartDateOfWeek());
                            preparedStatement.setDate(2, week.getLastDateOfWeek());
                            preparedStatement.execute();
                        }
                    }catch (SQLException e){
                        throw new SQLException(String.format("Save week bad request: %s", Arrays.toString(e.getStackTrace())));
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Week findById(Long idWeek) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getById)){
            preparedStatement.setLong(1, idWeek);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){

                    Week week = new Week();
                    week.setId(resultSet.getLong(1));
                    week.setStartDateOfWeek(resultSet.getDate(2));
                    week.setLastDateOfWeek(resultSet.getDate(3));
                    return week;
                }else{
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Week> getWeeks() {
        int year = LocalDate.now().getYear();
        LocalDate startOfYear = LocalDate.of(year, 1, 1);
        LocalDate endOfYear = LocalDate.of(year, 12, 31);

        List<Week> weeks = new ArrayList<>();

        LocalDate firstMonday = startOfYear.with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        if (firstMonday.isBefore(startOfYear)) {
            firstMonday = firstMonday.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        while (firstMonday.isBefore(endOfYear)) {
            LocalDate endOfWeek = firstMonday.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));

            Week scheduleDto = new Week();
            scheduleDto.setStartDateOfWeek(Date.valueOf(firstMonday));
            scheduleDto.setLastDateOfWeek(Date.valueOf(endOfWeek));
            weeks.add(scheduleDto);

            firstMonday = endOfWeek.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }
        return weeks;
    }


}
