package radion.app.authoshkola.model.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UserUpdateDto {

    private Long id;
    private String firstName;
    private String name;
    private String lastName;
    private Date birthday;
    private String email;
    private String phoneNumber;
    private Boolean isStudying;
}
