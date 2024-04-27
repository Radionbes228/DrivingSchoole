package radion.app.authoshkola.entity.dto;


import jakarta.validation.constraints.NotNull;

public class StudentLoginDTO{

    @NotNull
    private String email;

    @NotNull
    private String password;
}
