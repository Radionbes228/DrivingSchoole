package radion.app.authoshkola.model.users;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Admin {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String roles;
}
