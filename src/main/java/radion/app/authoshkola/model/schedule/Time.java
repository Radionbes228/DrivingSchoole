package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Time {
    @NotNull
    private Long id;

    @NotNull
    private String time;
}
