package radion.app.authoshkola.entity.schedule;

import lombok.Data;

@Data
public class Schedule {

    private Long id;

    private String year;

    private String startWeek;

    private String endWeek;
}
