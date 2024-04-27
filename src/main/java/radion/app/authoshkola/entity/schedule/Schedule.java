package radion.app.authoshkola.entity.schedule;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Schedule {

    private Long id;

    private Integer year;

    private Date startWeek;

    private Date endWeek;
}
