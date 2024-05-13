package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class WeeksStudents {

    @NotNull
    private Long id;

    @NotNull
    private Long weekId;

    @NotNull
    private Long dayId;

    @NotNull
    private Long studentId;

    @NotNull
    private Long time_id;
}
