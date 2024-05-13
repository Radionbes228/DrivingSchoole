package radion.app.authoshkola.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class ScheduleDto {
    private String year;

    private Date startWeek;

    private Date endWeek;
}
