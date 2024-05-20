package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Groups {

    private Long id;

    private Integer groupNumber;

    private Long instructorId;
}
