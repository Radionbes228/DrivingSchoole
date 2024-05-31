package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.dto.AdminDto;
import radion.app.authoshkola.model.dto.InstructorInfoDto;
import radion.app.authoshkola.model.users.User;
import java.util.List;

public interface AdminService {
    AdminDto findByEmail(String email);
}
