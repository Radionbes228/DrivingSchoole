package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.entity.users.Student;
import radion.app.authoshkola.entity.dto.StudentLoginDTO;
import radion.app.authoshkola.entity.roles.RolesEnum;
import radion.app.authoshkola.service.StudentService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class SecurityController {

    private StudentService studentService;

    private final List<String> dayOfWeek = new ArrayList<>();
    {
        dayOfWeek.add("monday");
        dayOfWeek.add("tuesday");
        dayOfWeek.add("wednesday");
        dayOfWeek.add("thursday");
        dayOfWeek.add("friday");
    }

    @GetMapping("/")
    public String loginGet(){
        return "login_page";
    }

    @PostMapping("/")
    public String loginPost(StudentLoginDTO student){
        return "redirect:/";
    }

    @GetMapping("/new-user")
    public String newStudentGet(){
        return "new_student";
    }

    @PostMapping("/new-user")
    public String nweStudentPost(Student student){
        student.setIsStudying(true);
        student.setRoles(String.valueOf(RolesEnum.STUDENT));
        studentService.save(student);
        return "redirect:/auth";
    }

    @GetMapping("/schedule")
    public String schedule(Model model){
        model.addAttribute("weeks", dayOfWeek);
        return "new_schedules_week";
    }

    @PostMapping("/schedule")
    public String schedulePost(){
        return "redirect:/";
    }

    @GetMapping("/test")
    public String test() throws ClassNotFoundException {
        return "redirect:/new-user";
    }

}
