package radion.app.authoshkola.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import radion.app.authoshkola.model.dto.InstructorInfoDto;
import radion.app.authoshkola.model.dto.InstructorUpdateDto;
import radion.app.authoshkola.model.roles.RolesEnum;
import radion.app.authoshkola.model.schedule.Record;
import radion.app.authoshkola.model.schedule.Time;
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
@RequestMapping("/instructor")
public class InstructorController {
    private final InstructorService instructorService;
    private final StudentService studentService;
    private final WeekService weekService;
    private final RecordService recordService;
    private final DayService dayService;
    private final TimeService timeService;
    private final UserService userService;

    @GetMapping("/list/{id_week}")
    public String allInstructors(Model model,
                                 @PathVariable("id_week") Long id_week,
                                 Principal principal){
        model.addAttribute("instructors", instructorService.findAll());
        model.addAttribute("select_week", id_week);
        model.addAttribute("select_week_info", weekService.findById(id_week));
        model.addAttribute("role", userService.findRoleByEmail(principal.getName()));
        return "instructor/instructors_list";
    }

    @GetMapping("/create/{id_week}")
    public String createInstructor(Model model, @PathVariable("id_week") Long id_week){
        model.addAttribute("birthday", LocalDate.now().toString());
        model.addAttribute("select_week", id_week);
        model.addAttribute("role", RolesEnum.ROLE_INSTRUCTOR.toString());
        return "instructor/create_form_instructors";
    }

    @PostMapping("/create")
    public String createInstructor(@RequestParam("id_week") Long id_week,
                                   @Validated User instructor,
                                   BindingResult bindingResult,
                                   Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("birthday", instructor.getBirthday() == null ? LocalDate.now().toString(): instructor.getBirthday());
            model.addAttribute("select_week", id_week);
            model.addAttribute("role", RolesEnum.ROLE_INSTRUCTOR.toString());
            model.addAttribute("user", instructor);
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    map.put(error.getField(), error.getDefaultMessage())
            );
            model.addAttribute("errors", map);
            return "instructor/create_form_instructors";
        }else {
            try {
                instructorService.save(instructor);
                return "redirect:/instructors";
            }catch (RuntimeException e){
                model.addAttribute("birthday", instructor.getBirthday() == null ? LocalDate.now().toString(): instructor.getBirthday());
                model.addAttribute("select_week", id_week);
                model.addAttribute("role", RolesEnum.ROLE_INSTRUCTOR.toString());
                model.addAttribute("user", instructor);
                HashMap<String, String> map = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error ->
                        map.put(error.getField(), error.getDefaultMessage())
                );
                model.addAttribute("email_exception", "Такая почта уже используется!");
                model.addAttribute("errors", map);
                return "instructor/create_form_instructors";
            }
        }
    }

    @GetMapping("/update/{id_week}/{instructor_id}")
    public String updateFormInstructor(@PathVariable("id_week") Long id_week,
                                       @PathVariable("instructor_id") Long instructor_id,
                                       Model model){
        model.addAttribute("instructor", instructorService.findById(instructor_id));
        model.addAttribute("select_week", id_week);
        return "instructor/update_instructor";
    }

    @PostMapping("/update")
    public String updateInstructor(@RequestParam("id_week") Long id_week,
                                   @Validated InstructorUpdateDto instructor,
                                   BindingResult bindingResult,
                                   Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("instructor", instructor);
            model.addAttribute("select_week", id_week);
            HashMap<String, String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    map.put(error.getField(), error.getDefaultMessage())
            );
            model.addAttribute("errors", map);
            return "instructor/update_instructor";
        }else {
            instructorService.update(instructor);
            return "redirect:/instructor/list/"+id_week;
        }
    }

    @GetMapping("/profile/{id_week}")
    public String home(Model model, @PathVariable("id_week") Long id_week, Principal principal){

        InstructorInfoDto instructorInfoDto = instructorService.getUserInfo(principal.getName());

        List<Week> weekList = weekService.findAllWeek();
        model.addAttribute("user_info", instructorInfoDto);
        model.addAttribute("record_of_weeks", recordService.findAllRecordViewByWeek(id_week));
        model.addAttribute("weeks", weekList);
        model.addAttribute("select_week", id_week);
        model.addAttribute("select_week_info", weekService.findById(id_week));
        return "profiles/profile";
    }

    @GetMapping("record/{id_group}/{id_day}/{id_week}")
    public String record(Model model,
                         @PathVariable("id_week") Long id_week,
                         @PathVariable("id_day") Long id_day,
                         @PathVariable("id_group") Long id_group){
        model.addAttribute("week", weekService.findById(id_week));
        model.addAttribute("group_id", id_group);
        model.addAttribute("day", dayService.findById(id_day));
        model.addAttribute("students", studentService.findAllByGroup(id_group));
        model.addAttribute("times", timeService.findAllByGroupWeekDay(id_week, id_day, id_group));
        for (Time t: timeService.findAllByGroupWeekDay(id_week, id_day, id_group)) {
            System.out.println(t.getTime());
        }
        return "student/record_student";
    }


    @PostMapping("/record")
    public String recordPost(Record record,
                             @RequestParam("id_group") Long id_group){
        recordService.save(record);
        return "redirect:/instructor/record/{id_group}/" + record.getDayOfDayId() + "/" + record.getWeekId();
    }
}
