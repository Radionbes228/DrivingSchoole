/*
package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import radion.app.authoshkola.model.schedule.Schedule;
import radion.app.authoshkola.model.schedule.Record;
import radion.app.authoshkola.repositories.*;

import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/student-weeks")
public class StudentWeeksController {
    private WeeksStudentService weeksStudentService;
    private ScheduleService scheduleService;
    private DayService dayService;
    private StudentService studentService;
    private TimeService timeService;



    @GetMapping
    public String viewSchedule(Model model, Principal principal){
        var schedule = scheduleService.findAll();
        var preview = weeksStudentService.viewScheduleOfWeek(3L);
        model.addAttribute("weeks", schedule);
        for (Schedule s : schedule) {
            if (s.getId().equals(3L)){
                model.addAttribute("week", s);
            }
        }
        model.addAttribute("schedule_preview", preview);
        return "schedule/preview_schedule";
    }

    @PostMapping
    public String viewSchedule(@RequestParam("id_week") Long id_week,  Model model, Principal principal){

        var schedule = scheduleService.findAll();
        var preview = weeksStudentService.viewScheduleOfWeek(id_week);
        model.addAttribute("weeks", schedule);
        for (Schedule s : schedule) {
            if (s.getId().equals(id_week)){
                model.addAttribute("week", s);
            }
        }
        model.addAttribute("schedule_preview", preview);
        return "schedule/preview_schedule";
    }

    @GetMapping("/create")
    public String record(Model model){
        model.addAttribute("weeks", scheduleService.findAll());
        model.addAttribute("days", dayService.findAll());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("times", timeService.findAll());
        return "student/record_student";
    }

    @PostMapping("/create")
    public String recordPost(Record record){
        weeksStudentService.save(record);
        return "redirect:/student_weeks";
    }
}
*/
