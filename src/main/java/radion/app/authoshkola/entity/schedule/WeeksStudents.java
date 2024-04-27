package radion.app.authoshkola.entity.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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

    @NotNull
    private Long responsible;
}
