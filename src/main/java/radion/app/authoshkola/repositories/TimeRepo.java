package radion.app.authoshkola.repositories;

import org.springframework.stereotype.Repository;
import radion.app.authoshkola.entity.schedule.Time;

import java.util.List;

public interface TimeRepo {
    List<Time> findAll();
    Time findByTime(Time time);
    Time findById(Long id);
    void save(Time time);
    void delete(Long id);
}
