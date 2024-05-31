package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DayOfWeek {

    private Long id;

    @NotNull(message = "{null.point.exception.day.of.week}")
    private String dayName;
}
