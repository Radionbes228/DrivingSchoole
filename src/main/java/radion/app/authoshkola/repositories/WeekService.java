package radion.app.authoshkola.repositories;


import radion.app.authoshkola.model.schedule.Week;

import java.util.List;

public interface WeekService {
    List<Week> findAllWeek();
    void save();

    Week findById(Long idWeek);
}
