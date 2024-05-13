package radion.app.authoshkola.model.schedule;

import lombok.Data;

import java.sql.Date;

@Data
public class Schedule {

    private Long id;

    private String year;

    private Date startWeek;

    private Date endWeek;
}
