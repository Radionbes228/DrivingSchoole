package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.schedule.Schedule;
import radion.app.authoshkola.repositories.ScheduleRepo;
import radion.app.authoshkola.repositories.StudentRepo;
import radion.app.authoshkola.repositories.TimeRepo;
import radion.app.authoshkola.service.ScheduleService;
import radion.app.authoshkola.service.StudentService;
import radion.app.authoshkola.service.TimeService;

@Controller
@AllArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private ScheduleRepo scheduleService;
    private TimeRepo timeService;
    private StudentRepo studentService;

    @GetMapping
    private String allSchedule(Model model){
        scheduleService.saveAndSchedule();
        model.addAttribute("schedules", scheduleService.findAll());
        return "schedule/schedules";
    }

    @GetMapping("/{id}")
    private String infoSchedule(@PathVariable("id") Long id, Model model){
        model.addAttribute("schedule", scheduleService.findById(id));
        model.addAttribute("year", timeService.getYear());
        return "schedule/info_schedule";
    }

    @GetMapping("/create")
    private String createSchedule(Model model) {
        model.addAttribute("year", timeService.getYear());
        model.addAttribute("students", studentService.findAll());
        model.addAttribute("schedule", new Schedule());
        return "schedule/create_schedule";
    }

    @PostMapping("/create")
    private String saveSchedule(Schedule schedule){
        scheduleService.save(schedule);
        return "redirect:/schedules";
    }

    @PostMapping("/update")
    public String updateWeek(Schedule schedule){
        scheduleService.save(schedule);
        return "redirect:/schedules";
    }

    @GetMapping("/update/{id}")
    public String updateFormWeek(@PathVariable Long id, Model model){
        model.addAttribute("schedule", scheduleService.findById(id));
        model.addAttribute("year", timeService.getYear());
        return "schedule/update_schedule";
    }
}
