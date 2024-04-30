package radion.app.authoshkola.repositories;

import radion.app.authoshkola.entity.schedule.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleRepo {
    List<Schedule> findAll();
    Schedule findByYearAndStartWeek(Integer year, Date date);
    Schedule findById(Long id);
    void save(Schedule schedule);
}
