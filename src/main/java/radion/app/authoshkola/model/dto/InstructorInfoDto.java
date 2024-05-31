package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String firstName;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private Date birthday;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private List<Groups> groups;
    @NotNull
    private String role;
}
