package radion.app.authoshkola.entity.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class DayOfWeek {
    @NotNull
    private Long id;

    @NotNull
    private String dayName;
}
