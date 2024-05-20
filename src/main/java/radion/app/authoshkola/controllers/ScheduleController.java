/*
package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.schedule.Schedule;
import radion.app.authoshkola.repositories.ScheduleService;

@Controller
@AllArgsConstructor
@RequestMapping("/schedules")
public class ScheduleController {
    private ScheduleService scheduleService;

    @GetMapping
    private String allSchedule(Model model){
        scheduleService.saveAndSchedule();
        model.addAttribute("schedules", scheduleService.findAll());
        return "schedule/schedules";
    }
}
*/
