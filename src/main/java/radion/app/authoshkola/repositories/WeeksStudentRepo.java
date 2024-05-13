package radion.app.authoshkola.repositories;


import radion.app.authoshkola.model.dto.ViewSchedule;
import radion.app.authoshkola.model.schedule.WeeksStudents;

import java.util.List;

public interface WeeksStudentRepo {
    List<WeeksStudents> findAll();
    WeeksStudents findById(Long id);
    WeeksStudents findForWeekId(Long id);
    long getFirstWeek();
    List<ViewSchedule> viewScheduleOfWeek(Long id_week);

    void save(WeeksStudents student);
    void update(WeeksStudents student);
    void delete(Long id);
}
