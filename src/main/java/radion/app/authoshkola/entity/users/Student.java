package radion.app.authoshkola.entity.users;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Student {
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
    private Integer age;

    @NotNull
    private Long group_id;

    @NotNull
    private String roles;

    @NotNull
    private Boolean isStudying;
}
