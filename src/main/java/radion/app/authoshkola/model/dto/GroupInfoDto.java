package radion.app.authoshkola.model.dto;

import lombok.Data;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.model.users.Instructors;


@Data
public class GroupInfoDto {

    private Groups group;

    private Instructors instructor;

    private Integer countStudent;
}
