package radion.app.authoshkola.entity;


import jakarta.validation.constraints.NotNull;

public class StudentLoginDTO extends Student{

    @NotNull
    private String email;

    @NotNull
    private String password;
}
