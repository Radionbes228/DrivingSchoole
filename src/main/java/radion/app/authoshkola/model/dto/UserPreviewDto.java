package radion.app.authoshkola.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserPreviewDto {

    private Long id;
    @NotNull
    private String name;

    private String email;

    @NotNull
    private String phoneNumber;

    @NotNull
    private Integer age;

    @NotNull
    private Boolean isStudying;
}
