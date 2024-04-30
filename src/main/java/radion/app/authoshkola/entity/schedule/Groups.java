package radion.app.authoshkola.entity.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
public class Groups {
    @NotNull
    private Long id;

    @NotNull
    private Integer groupNumber;

    @NotNull
    private Long instructorId;
}
