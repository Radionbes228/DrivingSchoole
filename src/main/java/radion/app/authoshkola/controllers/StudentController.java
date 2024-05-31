package radion.app.authoshkola.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.dto.UserUpdateDto;
import radion.app.authoshkola.model.roles.RolesEnum;
import radion.app.authoshkola.model.schedule.Week;
import radion.app.authoshkola.model.users.User;
import radion.app.authoshkola.repositories.*;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

//TODO
@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final RecordService recordService;
    private final WeekService weekService;
    private final GroupService groupService;
    private final UserService userService;

    @GetMapping("/group/{id_group}/{id_week}")
    public String allStudent(@PathVariable("id_group") Long groupId,
                             @PathVariable("id_week") Long id_week,
                             Principal principal, Model model) {

        model.addAttribute("students", studentService.findAllByGroup(groupId));
        model.addAttribute("instructor", instructorService.findByInstructorForIdGroup(groupId));
        model.addAttribute("group", groupService.findById(groupId));
        model.addAttribute("select_week", id_week);
        model.addAttribute("role", userService.findRoleByEmail(principal.getName()));
        return "student/student_group_list";
    }

    @GetMapping("/create/{id_week}")
    public String createStudent(Model model, @PathVariable Long id_week){
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("role", String.valueOf(RolesEnum.ROLE_STUDENT));
        model.addAttribute("select_week", id_week);
        model.addAttribute("birthday", LocalDate.now().toString());
        return "student/create_student";
    }

    @PostMapping("/create")
    public String saveStudent(@RequestParam("id_week") Long id_week,
                              @RequestParam("group_id")
                                    @NotNull(message = "{null.point.exception.group}") Long groupId,
                              @Validated User student,
                              BindingResult bindingResult,
                              Model model){

        if (bindingResult.hasErrors()){
            model.addAttribute("role", student.getRole());
            model.addAttribute("user", student);
            model.addAttribute("birthday", student.getBirthday().toString());
            model.addAttribute("group_select", groupId);
            model.addAttribute("groups", groupService.findAll());
            model.addAttribute("select_week", id_week);
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    map.put(error.getField(), error.getDefaultMessage())
            );
            model.addAttribute("errors", map);
            return "student/create_student";
        }else {
            studentService.save(student, groupId);
            return "redirect:/student/all/" + id_week ;
        }
    }

    @GetMapping("/update/{id_student}/{id_week}")
    public String updateFormStudent(@PathVariable("id_student") Long idStudent, @PathVariable("id_week") Long idWeek, Model model){
        UserUpdateDto student = studentService.findByIdUpdate(idStudent);
        model.addAttribute("student", student);
        model.addAttribute("group", student.getGroupId() != null ? groupService.findById(student.getGroupId()) : null);
        model.addAttribute("groups", groupService.findAll());
        model.addAttribute("select_week", idWeek);
        model.addAttribute("birthday", student.getBirthday().toString());
        return "student/update_form_student";
    }

    @PostMapping("/update")
    public String updateStudent(
            @RequestParam("id_week") Long weekId,
            @Validated UserUpdateDto student,
            BindingResult bindingResult,
            Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("student", student);
            if (student.getGroupId() != null){
                model.addAttribute("group", groupService.findById(student.getGroupId()));
            }
            model.addAttribute("groups", groupService.findAll());
            model.addAttribute("select_week", weekId);
            model.addAttribute("birthday", student.getBirthday().toString());
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                        map.put(error.getField(), error.getDefaultMessage())
            );
            model.addAttribute("errors", map);
            bindingResult.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
            return "student/update_form_student";
        }else {
            studentService.update(student);
            return "redirect:/student/all/" + weekId;
        }
    }

    @GetMapping("/profile/{id_week}")
    public String home(Model model, @PathVariable("id_week") Long id_week, Principal principal){

        StudentInfoDto studentInfoDto = studentService.getUserInfo(principal.getName());

        List<Week> weekList = weekService.findAllWeek();
        model.addAttribute("user_info", studentInfoDto);
        model.addAttribute("record_of_weeks", recordService.findAllRecordViewByWeek(id_week));
        model.addAttribute("weeks", weekList);
        model.addAttribute("select_week", id_week);
        model.addAttribute("select_week_info", weekService.findById(id_week));
        return "profiles/profile";
    }

    @GetMapping("/all/{id_week}")
    public String allStudent(Model model, @PathVariable Long id_week){
        model.addAttribute("students", studentService.findAllPreview());
        model.addAttribute("select_week", id_week);
        return "student/students";
    }
}
