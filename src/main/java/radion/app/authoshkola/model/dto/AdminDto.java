package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminDto {
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
    private String role;
}
