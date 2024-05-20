package radion.app.authoshkola.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import radion.app.authoshkola.model.schedule.Groups;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InstructorInfoDto {
    private Long id;
    private String firstName;
    private String name;
    private String lastName;
    private Date birthday;
    private String email;
    private String phoneNumber;
    private List<Groups> groups;
    private String role;
}
