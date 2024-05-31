package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;

@Data
public class UserPreviewDto {

    private Long id;
    @NotNull
    private String firstName;
    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String email;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Date birthday;
    @NotNull
    private String role;
    @NotNull
    private Boolean isStudying;
    @NotNull
    private String groupNumber;
}
