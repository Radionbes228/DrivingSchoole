package radion.app.authoshkola.entity.users;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Instructors {
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private Integer age;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String roles;
}
