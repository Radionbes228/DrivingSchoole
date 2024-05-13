package radion.app.authoshkola.model.users;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Student {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    private String email;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer age;

    private Long group_id;

    @NotNull
    private String role;

    @NotNull
    private Boolean isStudying;
}
