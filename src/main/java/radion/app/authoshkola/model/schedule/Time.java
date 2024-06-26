package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {
    @NotNull
    private Long id;
    @NotNull
    private java.sql.Time time;
}
