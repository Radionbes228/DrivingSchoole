package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import radion.app.authoshkola.model.dto.StudentUpdateDto;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.roles.RolesEnum;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.model.schedule.Schedule;
import radion.app.authoshkola.model.users.Student;
import radion.app.authoshkola.repositories.*;

import java.security.Principal;
import java.util.HashMap;

@Controller
@AllArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private WeeksStudentRepo weeksStudentService;
    private ScheduleRepo scheduleService;
    private StudentRepo studentService;
    private GroupsRepo groupsService;

    @GetMapping
    public String allStudent(Model model) {
        HashMap<Student, Groups> studentIntegerHashMap = new HashMap<>();
        for (Student student : studentService.findAll()) {
            studentIntegerHashMap.put(student, groupsService.findById(student.getGroup_id()));
        }
        model.addAttribute("students_group", studentIntegerHashMap);
        return "student/students";
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
    public String updateStudent(StudentUpdateDto student){
        studentService.update(student);
        return "redirect:/students";
    }

    @GetMapping("/student-profile")
    public String home(Model model, Principal principal){

        StudentInfoDto studentInfoDto = studentService.getUserInfo(principal.getName());
        var schedule = scheduleService.findAll();
        var preview = weeksStudentService.viewScheduleOfWeek(3L);
        model.addAttribute("weeks", schedule);
        for (Schedule s : schedule) {
            if (s.getId().equals(3L)){
                model.addAttribute("week", s);
            }
        }
        model.addAttribute("schedule_preview", preview);
        model.addAttribute("user_info", studentInfoDto);
        model.addAttribute("weekSchedules", weeksStudentService.findAll());
        return "profiles/student_profile";
    }

    @GetMapping("/student-group-list/{group_id}")
    private String studentListOfGroup(Model model, @PathVariable("group_id") Long group_id){
        model.addAttribute("studentsGroup", studentService.findFindByStudentForGroupid(group_id));
        return "student/student_group_list";
    }
}
