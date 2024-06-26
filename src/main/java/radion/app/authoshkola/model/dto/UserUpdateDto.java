package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

@Data
public class UserUpdateDto {

    @NotNull(message = "{null.point.exception.id}")
    private Long id;
    @NotNull(message = "{null.point.exception.first.name}")
    @Size(message = "{size.exception}", min = 2, max = 150)
    private String firstName;
    @NotNull(message = "{null.point.exception.name}")
    @Size(message = "{size.exception}", min = 2, max = 150)
    private String name;

    private String lastName;
    @NotNull(message = "{null.point.exception.birthday}")
    private Date birthday;
    @NotNull(message = "{null.point.exception.email}")
    @Email
    private String email;
    @NotNull(message = "{null.point.exception.phone.number}")
    @Size(message = "{size.exception}", min = 10, max = 10)
    private String phoneNumber;

    private Long groupId;
    @NotNull(message = "{null.point.exception.is.studying}")
    private Boolean isStudying;
}
