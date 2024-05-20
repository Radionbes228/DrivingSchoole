package radion.app.authoshkola.model.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String name;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private Boolean isStudying;
}
