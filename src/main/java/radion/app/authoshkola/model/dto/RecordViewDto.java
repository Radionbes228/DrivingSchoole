package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Time;


@Data
public class RecordViewDto {
    private Long recordId;
    @NotNull
    private String firstName;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String dayOfWeek;
    @NotNull
    private Long weekId;
    @NotNull
    private Time time;
    @NotNull
    private Long groupId;
}
