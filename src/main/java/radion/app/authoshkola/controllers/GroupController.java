package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.apache.catalina.Group;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.repositories.GroupsRepo;
import radion.app.authoshkola.repositories.InstructorRepo;
import radion.app.authoshkola.repositories.StudentRepo;
import radion.app.authoshkola.service.GroupsService;
import radion.app.authoshkola.service.InstructorService;
import radion.app.authoshkola.service.StudentService;

@Controller
@AllArgsConstructor
@RequestMapping("/groups")
public class GroupController {
    private GroupsRepo groupsService;
    private InstructorRepo instructorService;
    private StudentRepo studentService;

    @GetMapping
    private String allGroup(Model model){
        model.addAttribute("groupsDTO", groupsService.getListForGroupsDTO());
        return "group/groups";
    }

    @GetMapping("/{id}")
    private String infoGroup(@PathVariable("id") Long id, Model model){
        Groups groups = groupsService.findById(id);
        model.addAttribute("group", groups);
        model.addAttribute("count", groupsService.countStudent(id));
        model.addAttribute("instructor", instructorService.findById(groups.getInstructorId()));
        return "group/info_group";
    }

    @GetMapping("/create")
    private String createFormGroup(Model model){
        model.addAttribute("instructors", instructorService.findAll());
        return "group/create_group";
    }

    @PostMapping("/create")
    private String createGroup(Groups groups){
        groupsService.save(groups);
        return "redirect:/groups";
    }

    @GetMapping("/update/{id}")
    private String updateFormGroup(@PathVariable("id") Long id, Model model){
        Groups groups = groupsService.findById(id);
        model.addAttribute("instructors", instructorService.findAll());
        model.addAttribute("group", groups);
        model.addAttribute("students", studentService.findFindByStudentForGroupid(id));
        return "group/update_group";
    }

    @PostMapping("/update")
    private String updateGroup(Groups groups){
        groupsService.update(groups);
        return "redirect:/groups";
    }
}
