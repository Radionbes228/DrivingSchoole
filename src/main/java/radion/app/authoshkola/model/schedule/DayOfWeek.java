package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DayOfWeek {
    @NotNull
    private Long id;

    @NotNull
    private String dayName;
}
