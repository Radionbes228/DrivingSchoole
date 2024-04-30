package radion.app.authoshkola.entity.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Time {
    @NotNull
    private Long id;

    @NotNull
    private Timestamp time;
}
