package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

@Data
public class InstructorUpdateDto {
    private Long id;
    @NotBlank(message = "{null.point.exception.first.name}")
    private String firstName;
    @NotBlank(message = "{null.point.exception.name}")
    @Size(message = "{size.exception}", min = 2, max = 150)
    private String name;

    private String lastName;
    @NotNull(message = "{null.point.exception.birthday}")
    private Date birthday;
    @NotBlank(message = "{null.point.exception.email}")
    @Email
    private String email;
    @NotBlank(message = "{null.point.exception.phone.number}")
    @Size(message = "{size.exception}", min = 10, max = 10)
    private String phoneNumber;
}
