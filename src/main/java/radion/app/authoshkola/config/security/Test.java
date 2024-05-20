/*
package radion.app.authoshkola.config.security;


import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.ScheduleDto;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class Test {
    ConnectionJDBC connectionJDBC = new ConnectionJDBC();
    private final String getAll = """
            select * from week order by date_of_week;
            """;
    private final String saveSchedule = """
            insert into week(date_of_week) values(?);
            """;
    public static void main(String[] args) {

    }


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
}
*/
