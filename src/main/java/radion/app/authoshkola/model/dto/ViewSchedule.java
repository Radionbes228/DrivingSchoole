package radion.app.authoshkola.model.dto;

import lombok.Data;


@Data
public class ViewSchedule {
    private Long recordId;
    private String nameStudent;
    private String dayOfWeek;
    private Long weekId;
    private String time;
}
