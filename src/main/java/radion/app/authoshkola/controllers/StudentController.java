package radion.app.authoshkola.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.dto.StudentInfoDto;
import radion.app.authoshkola.model.schedule.Week;
import radion.app.authoshkola.repositories.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final RecordService recordService;
    private final WeekService weekService;

    @PostMapping("/group")
    public String allStudent(@RequestParam("id_group") Long groupId, @RequestParam("id_week") Long id_week, Model model) {

        model.addAttribute("students", studentService.findAllByGroup(groupId));
        model.addAttribute("instructor", instructorService.findByInstructorForIdGroup(groupId));
        model.addAttribute("group", groupId);
        model.addAttribute("select_week", id_week);
        return "student/student_group_list";
    }
//
//    @GetMapping("/create")
//    public String createStudent(Model model){
//        model.addAttribute("groups", groupsService.findAll());
//        model.addAttribute("role", RolesEnum.STUDENT);
//        return "student/create_student";
//    }
//
//    @PostMapping("/create")
//    public String saveStudent(Student student){
//        studentService.save(student);
//        return "redirect:/students";
//    }
//
//    @GetMapping("/update/{id}")
//    public String updateFormStudent(@PathVariable("id") Long id, Model model){
//        Student student = studentService.findById(id);
//        model.addAttribute("student", student);
//        model.addAttribute("group", groupsService.findById(student.getGroup_id()));
//        model.addAttribute("groups", groupsService.findAll());
//        return "student/update_form_student";
//    }
//
//    @PostMapping("/update")
//    public String updateStudent(UserUpdateDto student){
//        studentService.update(student);
//        return "redirect:/students";
//    }
//
    @GetMapping("/profile/{id_week}")
    public String home(Model model, @PathVariable("id_week") Long id_week){

        StudentInfoDto studentInfoDto = studentService.getUserInfo("nuhul-esijo51@gmail.com");

        List<Week> weekList = weekService.findAllWeek();
        model.addAttribute("user_info", studentInfoDto);
        model.addAttribute("record_of_weeks", recordService.findAllRecordViewByWeek(id_week));
        model.addAttribute("weeks", weekList);
        model.addAttribute("select_week", id_week);
        return "profiles/profile";
    }

/*    @PostMapping("/student-profile")
    public String viewSchedule(@RequestParam("id_week") Long id_week, Model model, Principal principal){

        UserInfoDto userInfoDto = studentService.getUserInfo(principal.getName());
        var schedule = scheduleService.findAll();
        var preview = weeksStudentService.viewScheduleOfWeek(id_week);
        model.addAttribute("weeks", schedule);
        for (Schedule s : schedule) {
            if (s.getId().equals(id_week)){
                model.addAttribute("select_week", s);
            }
        }
        model.addAttribute("schedule_preview", preview);
        model.addAttribute("user_info", userInfoDto);
        model.addAttribute("weekSchedules", weeksStudentService.findAll());
        return "profiles/student_profile";
    }*/
//
//    @GetMapping("/student-group-list/{group_id}/{select_week}")
//    private String studentListOfGroup(Model model, @PathVariable("group_id") Long group_id, @PathVariable("select_week") Long select_week){
//        model.addAttribute("group", group_id);
//        model.addAttribute("instructor", instructorService.findById(groupsService.findById(group_id).getInstructorId()));
//        model.addAttribute("studentsGroup", studentService.findFindByStudentForGroupid(group_id));
//        model.addAttribute("select_week", select_week);
//        return "student/student_group_list";
//    }
//
//    @GetMapping("/instructors/{select_week}")
//    public String allInstructors(Model model, @PathVariable Long select_week){
//        model.addAttribute("instructors", instructorService.findAll());
//        model.addAttribute("select_week", select_week);
//        return "student/instructors_list";
//    }
}
