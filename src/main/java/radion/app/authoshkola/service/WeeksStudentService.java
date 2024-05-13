package radion.app.authoshkola.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import radion.app.authoshkola.ConnectionJDBC;
import radion.app.authoshkola.model.dto.ViewSchedule;
import radion.app.authoshkola.model.schedule.WeeksStudents;
import radion.app.authoshkola.repositories.WeeksStudentRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class WeeksStudentService implements WeeksStudentRepo {
    private ConnectionJDBC connectionJDBC;


    private final String getAll = """
            select * from weeks_students order by week_id;
            """;
    private final String getById = """
            select * from weeks_students where weeks_students_id = ?;
            """;
    private final String save = """
            insert into weeks_students(week_id, day_id, student_id, time_id) values(?,?,?,?);
            """;
    private final String update = """
            update weeks_students set week_id = ?, day_id = ?, student_id = ?, time = ?, responsible = ? where weeks_students_id = ?;
            """;
    private final String delete = """
            delete from weeks_students where weeks_students_id = ?;
            """;
    private final String getViewSchedue = """
            select weeks_students.weeks_students_id, student.name, day_of_week.day_name, schedule.schedule_id, time.time from weeks_students
                join student on weeks_students.student_id = student.student_id
                join day_of_week on day_of_week.day_id = weeks_students.day_id
                join schedule on schedule.schedule_id = weeks_students.week_id
                join public.day_of_week dow on dow.day_id = weeks_students.day_id
                join time on time.time_id = weeks_students.time_id;
            """;

    @Override
    public List<WeeksStudents> findAll() {
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(getAll)){
                List<WeeksStudents> weeksStudentsList = new ArrayList<>();
                while (resultSet.next()){
                    WeeksStudents weeksStudents = new WeeksStudents();

                    weeksStudents.setId(resultSet.getLong(1));
                    weeksStudents.setWeekId(resultSet.getLong(2));
                    weeksStudents.setDayId(resultSet.getLong(3));
                    weeksStudents.setStudentId(resultSet.getLong(4));
                    weeksStudents.setTime_id(resultSet.getLong(5));

                    weeksStudentsList.add(weeksStudents);
                }
                return weeksStudentsList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public WeeksStudents findById(Long id) {
        return null;
    }

    @Override
    public WeeksStudents findForWeekId(Long id) {
        return null;
    }

    @Override
    public long getFirstWeek() {
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement()){
            try (ResultSet resultSet = statement.executeQuery(getAll)){
                if (resultSet.next()){
                    return resultSet.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public List<ViewSchedule> viewScheduleOfWeek(Long id_week) {
        try (Connection connection = connectionJDBC.connect();
             Statement statement = connection.createStatement())   {
            try (ResultSet resultSet = statement.executeQuery(getViewSchedue)){
                List<ViewSchedule> viewScheduleList = new ArrayList<>();

                while (resultSet.next()){
                    if (resultSet.getLong(4) == id_week){
                        ViewSchedule viewSchedule = new ViewSchedule();

                        viewSchedule.setRecordId(resultSet.getLong(1));
                        viewSchedule.setNameStudent(resultSet.getString(2));
                        viewSchedule.setDayOfWeek(resultSet.getString(3));
                        viewSchedule.setWeekId(resultSet.getLong(4));
                        viewSchedule.setTime(resultSet.getString(5));

                        viewScheduleList.add(viewSchedule);
                    }
                }
                return viewScheduleList;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(WeeksStudents student) {
        try (Connection connection = connectionJDBC.connect();
             PreparedStatement statement = connection.prepareStatement(save)){

            statement.setLong(1, student.getWeekId());
            statement.setLong(2, student.getDayId());
            statement.setLong(3, student.getStudentId());
            statement.setLong(4, student.getTime_id());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(WeeksStudents student) {

    }

    @Override
    public void delete(Long id) {

    }
}
