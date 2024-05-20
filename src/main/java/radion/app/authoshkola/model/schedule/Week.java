package radion.app.authoshkola.model.schedule;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Week {
    private Long id;
    private Date startDateOfWeek;
    private Date lastDateOfWeek;
}
