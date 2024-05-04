package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import radion.app.authoshkola.entity.roles.RolesEnum;
import radion.app.authoshkola.entity.users.Instructors;
import radion.app.authoshkola.service.InstructorService;

@Controller
@AllArgsConstructor
@RequestMapping("/instructors")
public class InstructorsController {
    private InstructorService instructorService;

    @GetMapping
    public String allInstructors(Model model){
        model.addAttribute("instructors", instructorService.findAll());
        return "instructor/instructors";
    }

    @GetMapping("/{id}")
    public String infoInstructor(@PathVariable("id") Long id, Model model){
        model.addAttribute("instructor", instructorService.findById(id));
        return "instructor/info_instructor";
    }

    @GetMapping("/create")
    public String createInstructor(Model model){
        model.addAttribute("role", RolesEnum.INSTRUCTOR);
        return "instructor/create_form_instructors";
    }

    @PostMapping("/create")
    public String createInstructor(Instructors instructor){
        instructorService.save(instructor);
        return "redirect:/instructors";
    }

    @GetMapping("/update/{id}")
    public String updateFormInstructor(@PathVariable Long id, Model model){
        model.addAttribute("instructor", instructorService.findById(id));
        return "instructor/update_instructor";
    }

    @PostMapping("/update")
    public String updateInstructor(Instructors instructor){
        instructorService.update(instructor);
        return "redirect:/instructors";
    }

}
