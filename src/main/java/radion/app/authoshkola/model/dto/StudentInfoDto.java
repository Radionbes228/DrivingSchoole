package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import radion.app.authoshkola.model.schedule.Groups;

import java.sql.Date;

@Data
public class StudentInfoDto {

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
    private Groups group;
    @NotNull
    private String role;
    @NotNull
    private Boolean isStudying;

}
