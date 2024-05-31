package radion.app.authoshkola.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import radion.app.authoshkola.model.dto.AdminDto;
import radion.app.authoshkola.model.schedule.Week;
import radion.app.authoshkola.repositories.AdminService;
import radion.app.authoshkola.repositories.GroupService;
import radion.app.authoshkola.repositories.RecordService;
import radion.app.authoshkola.repositories.WeekService;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;
    private final RecordService recordService;
    private final WeekService weekService;
    private final GroupService groupService;


    @GetMapping("/profile/{id_week}")
    public String home(Model model, @PathVariable("id_week") Long id_week, Principal principal){

        AdminDto instructorInfoDto = adminService.findByEmail(principal.getName());

        List<Week> weekList = weekService.findAllWeek();
        model.addAttribute("user_info", instructorInfoDto);
        model.addAttribute("record_of_weeks", recordService.findAllRecordViewByWeek(id_week));
        model.addAttribute("weeks", weekList);
        model.addAttribute("select_week", id_week);
        model.addAttribute("select_week_info", weekService.findById(id_week));
        model.addAttribute("groups", groupService.findAll());
        return "profiles/profile";
    }
}
