package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.schedule.Time;

import java.util.List;

public interface TimeService {
    List<Time> findAll();
    List<Time> findAllByGroupWeekDay(Long week_id, Long id_day, Long group_id);
}
