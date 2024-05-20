package radion.app.authoshkola.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.dto.InstructorInfoDto;
import radion.app.authoshkola.model.schedule.Record;
import radion.app.authoshkola.model.schedule.Time;
import radion.app.authoshkola.model.schedule.Week;
import radion.app.authoshkola.repositories.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorsController {
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final WeekService weekService;
    private final RecordService recordService;
    private final DayService dayService;
    private final TimeService timeService;

    @PostMapping("/list")
    public String allInstructors(Model model,
                                 @RequestParam("id_week") Long id_week,
                                 @RequestParam("role") String role){
        model.addAttribute("instructors", instructorService.findAll());
        model.addAttribute("select_week", id_week);
        model.addAttribute("role", role);
        return "instructor/instructors_list";
    }

//    @GetMapping("/create")
//    public String createInstructor(Model model){
//        model.addAttribute("role", RolesEnum.INSTRUCTOR);
//        return "instructor/create_form_instructors";
//    }
//
//    @PostMapping("/create")
//    public String createInstructor(Instructors instructor){
//        instructorService.save(instructor);
//        return "redirect:/instructors";
//    }
//
//    @GetMapping("/update/{id}")
//    public String updateFormInstructor(@PathVariable Long id, Model model){
//        model.addAttribute("instructor", instructorService.findById(id));
//        return "instructor/update_instructor";
//    }
//
//    @PostMapping("/update")
//    public String updateInstructor(Instructors instructor){
//        instructorService.update(instructor);
//        return "redirect:/instructors";
//    }
//
    @GetMapping("/profile/{id_week}")
    public String home(Model model, @PathVariable("id_week") Long id_week){

        InstructorInfoDto studentInfoDto = instructorService.getUserInfo("pukaru-gigu4@gmail.com");

        List<Week> weekList = weekService.findAllWeek();
        model.addAttribute("user_info", studentInfoDto);
        model.addAttribute("record_of_weeks", recordService.findAllRecordViewByWeek(id_week));
        model.addAttribute("weeks", weekList);
        model.addAttribute("select_week", id_week);
        return "profiles/profile";
    }

    @GetMapping("record/{id_group}/{id_day}/{id_week}")
    public String record(Model model,
                         @PathVariable("id_week") Long id_week,
                         @PathVariable("id_day") Long id_day,
                         @PathVariable("id_group") Long id_group){
        model.addAttribute("week", weekService.findById(id_week));
        model.addAttribute("day", dayService.findByid(id_day));
        model.addAttribute("students", studentService.findAllByGroup(id_group));
        model.addAttribute("times", timeService.findAllByGroupWeekDay(id_week, id_day, id_group));
        for (Time t: timeService.findAllByGroupWeekDay(id_week, id_day, id_group)) {
            System.out.println(t.getTime());
        }
        return "student/record_student";
    }

    @PostMapping("/record")
    public String recordPost(Record record){
        recordService.save(record);
        return "redirect:/instructor/schedule-of-group";
    }
}
