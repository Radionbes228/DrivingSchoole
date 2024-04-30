package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import radion.app.authoshkola.entity.roles.RolesEnum;
import radion.app.authoshkola.entity.users.Student;
import radion.app.authoshkola.service.GroupsService;
import radion.app.authoshkola.service.StudentService;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private StudentService studentService;
    private GroupsService groupsService;

    @GetMapping
    public String allStudent(Model model){
        model.addAttribute("students", studentService.findAll());
        return "student/students";
    }

    @GetMapping("/{id}")
    public String infoStudent(@PathVariable("id") Long id, Model model){
        model.addAttribute("student", studentService.findById(id));
        model.addAttribute("group", groupsService.findById(id));
        return "student/student_info";
    }

    @GetMapping("/create")
    public String createStudent(Model model){
        model.addAttribute("groups", groupsService.findAll());
        model.addAttribute("role", RolesEnum.STUDENT);
        return "student/create_student";
    }

    @PostMapping("/create")
    public String saveStudent(Student student){
        studentService.save(student);
        return "redirect:/students";
    }

    @GetMapping("/update/{id}")
    public String updateFormStudent(@PathVariable("id") Long id, Model model){
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("group", groupsService.findById(student.getGroup_id()));
        model.addAttribute("groups", groupsService.findAll());
        return "student/update_form_student";
    }

    @PostMapping("/update")
    public String updateStudent(Student student){
        studentService.update(student);
        return "redirect:/students";
    }
}
