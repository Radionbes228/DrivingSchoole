package radion.app.authoshkola.model.dto;

import lombok.Data;

@Data
public class StudentInfoDto {

    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private Long groupId;
    private Integer age;

}
