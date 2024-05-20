package radion.app.authoshkola.model.dto;

import lombok.Data;

import java.sql.Time;


@Data
public class RecordViewDto {
    private Long recordId;
    private String firstName;
    private String name;
    private String lastName;
    private String dayOfWeek;
    private Long weekId;
    private Time time;
    private Long groupId;
}
