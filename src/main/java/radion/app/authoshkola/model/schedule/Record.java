package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Record {

    private Long id;

    @NotNull
    private Long weekId;

    @NotNull
    private Long dayOfDayId;

    @NotNull
    private Long studentId;

    @NotNull
    private Long timeId;
}
