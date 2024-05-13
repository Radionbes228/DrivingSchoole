package radion.app.authoshkola.model.dto;

import lombok.Data;

@Data
public class StudentUpdateDto{

    private Long id;

    private String name;

    private String email;

    private String phoneNumber;

    private Integer age;

    private Long group_id;

    private Boolean isStudying;
}
