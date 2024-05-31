package radion.app.authoshkola.controllers;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.schedule.Groups;
import radion.app.authoshkola.model.schedule.Week;
import radion.app.authoshkola.repositories.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/group")
public class GroupController {
    private WeekService weekService;
    private RecordService recordService;
    private GroupService groupService;
    private InstructorService instructorService;

    @GetMapping("/create/{id_week}")
    private String createFormGroup(Model model, @PathVariable Long id_week){
        model.addAttribute("instructors", instructorService.findAll());
        model.addAttribute("select_week", id_week);
        return "group/create_group";
    }

    @PostMapping("/create")
    @SneakyThrows
    private String createGroup(@Validated Groups groups,
                               BindingResult bindingResult,
                               @RequestParam("id_week") Long id_week,
                               Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("instructors", instructorService.findAll());
            model.addAttribute("group_info", groups);
            model.addAttribute("select_week", id_week);
            HashMap<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            model.addAttribute("errors", errors);
            return "group/create_group";
        }else {
            try {
                groupService.save(groups);
                return "redirect:/admin/profile/" + id_week;
            }catch (RuntimeException e){
                model.addAttribute("instructors", instructorService.findAll());
                model.addAttribute("group_info", groups);
                model.addAttribute("select_week", id_week);
                HashMap<String, String> errors = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
                model.addAttribute("errors", errors);
                model.addAttribute("sql_exception", e.getMessage());
                return "group/create_group";
            }
        }
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("group_id") Long idGroup, @RequestParam("id_week") Long id_week){
        groupService.delete(idGroup);
        return "redirect:/admin/profile/" + id_week;
    }

    @GetMapping("/update/{id_group}/{id_week}")
    private String updateFormGroup(@PathVariable("id_group") Long idGroup, Model model, @PathVariable("id_week") Long idWeek){
        Groups group = groupService.findById(idGroup);
        model.addAttribute("instructors", instructorService.findAll());
        model.addAttribute("group", group);
        model.addAttribute("select_week", idWeek);
        model.addAttribute("group_id", idGroup);
        return "group/update_group";
    }

    @PostMapping("/update")
    private String updateGroup(@Validated Groups group, @RequestParam("id_week") Long idWeek, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("instructors", instructorService.findAll());
            model.addAttribute("group", group);
            model.addAttribute("select_week", idWeek);
            HashMap<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            model.addAttribute("errors", errors);
            return "group/update_group";
        }else {
            groupService.update(group);
        }
        return "redirect:/groups";
    }

    @GetMapping("/schedule/{id_group}/{id_week}")
    public String schedule(
            @PathVariable("id_group") Long id_group,
            @PathVariable("id_week") Long id_week, Model model) {
        List<Week> weekList = weekService.findAllWeek();
        model.addAttribute("record_of_weeks", recordService.findAllRecordViewByWeek(id_week));
        model.addAttribute("weeks", weekList);
        model.addAttribute("select_week", id_week);
        model.addAttribute("id_group", id_group);
        return "group/schedule_of_group";
    }
}
