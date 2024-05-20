package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.schedule.DayOfWeek;

import java.util.List;

public interface DayService {
    List<DayOfWeek> findAll();

    DayOfWeek findByid(Long idDay);
}
