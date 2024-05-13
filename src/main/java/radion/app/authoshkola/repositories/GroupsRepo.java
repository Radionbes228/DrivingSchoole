package radion.app.authoshkola.repositories;

import radion.app.authoshkola.model.dto.GroupInfoDto;
import radion.app.authoshkola.model.schedule.Groups;

import java.util.List;

public interface GroupsRepo {
    List<Groups> findAll();
    Groups findById(Long id);
    void save(Groups group);
    void delete(Long id);
    void update(Groups group);
    Integer countStudent(Long id);
    List<GroupInfoDto> getListForGroupsDTO();

}
