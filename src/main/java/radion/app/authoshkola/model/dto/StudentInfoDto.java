package radion.app.authoshkola.model.dto;

import lombok.Data;
import radion.app.authoshkola.model.schedule.Groups;

import java.sql.Date;

@Data
public class StudentInfoDto {

    private Long id;
    private String firstName;
    private String name;
    private String lastName;
    private Date birthday;
    private String email;
    private String phoneNumber;
    private Groups group;
    private String role;
    private Boolean isStudying;

}