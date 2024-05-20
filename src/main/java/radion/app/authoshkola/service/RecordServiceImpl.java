package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.RecordViewDto;
import radion.app.authoshkola.model.schedule.Record;
import radion.app.authoshkola.repositories.RecordService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RecordServiceImpl implements RecordService {
    private ConnectionJDBC connectionJDBC;

    private final String getAllRecordView = """
            select record.record_id, users.users_first_name, users_name, users.users_last_name, day_of_week.day_name, week.week_id, time.time
                from record
                    join day_of_week on day_of_week.day_id = record.fk_day_of_week
                    join users on users.users_id = record.fk_student_id
                    join week on record.fk_week_id = week.week_id
                    join time on record.fk_time_of_record = time.time_id
                        where week.week_id = ? order by time;
    """;

    private final String save = """
            insert into record(fk_week_id, fk_day_of_week, fk_student_id, fk_time_of_record)
                        VALUES
                            (?,?,?,?);
    """;

    @Override
    public List<RecordViewDto> findAllRecordViewByWeek(Long id_week) {
        List<RecordViewDto> recordViewDtos = new ArrayList<>();
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(getAllRecordView)){
            preparedStatement.setLong(1, id_week);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    do {
                        RecordViewDto recordViewDto = new RecordViewDto();

                        recordViewDto.setRecordId(resultSet.getLong(1));
                        recordViewDto.setFirstName(resultSet.getString(2));
                        recordViewDto.setName(resultSet.getString(3));
                        recordViewDto.setLastName(resultSet.getString(4));
                        recordViewDto.setDayOfWeek(resultSet.getString(5));
                        recordViewDto.setWeekId(resultSet.getLong(6));
                        recordViewDto.setTime(resultSet.getTime(7));

                        recordViewDtos.add(recordViewDto);
                    }while (resultSet.next());

                }
            }
            return recordViewDtos;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<RecordViewDto> findAllRecordViewByWeekInst(Long id_week) {
        return null;
    }

    @Override
    public void save(Record record) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(save)){

            preparedStatement.setLong(1, record.getWeekId());
            preparedStatement.setLong(2, record.getDayOfDayId());
            preparedStatement.setLong(3, record.getStudentId());
            preparedStatement.setLong(4, record.getTimeId());

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
