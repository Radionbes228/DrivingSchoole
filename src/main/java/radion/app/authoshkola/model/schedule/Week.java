package radion.app.authoshkola.model.schedule;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Week {
    private Long id;
    @NotNull
    private Date startDateOfWeek;
    @NotNull
    private Date lastDateOfWeek;
}
