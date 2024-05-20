package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.dto.RecordViewDto;
import radion.app.authoshkola.model.schedule.Record;

import java.util.List;

public interface RecordService {
    List<RecordViewDto> findAllRecordViewByWeek(Long id_week);
    List<RecordViewDto> findAllRecordViewByWeekInst(Long id_week);

    void save(Record record);
}
