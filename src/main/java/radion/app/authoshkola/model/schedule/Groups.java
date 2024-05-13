package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Groups {
    @NotNull
    private Long id;

    @NotNull
    private Integer groupNumber;

    private Long instructorId;
}
