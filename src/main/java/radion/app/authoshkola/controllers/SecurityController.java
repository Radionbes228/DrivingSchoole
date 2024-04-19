package radion.app.authoshkola.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import radion.app.authoshkola.entity.StudentLoginDTO;

@Controller
@RequestMapping("/auth")
public class SecurityController {
    @GetMapping("/")
    public String loginGet(){
        return "login_page";
    }

    @PostMapping("/")
    public String loginPost(StudentLoginDTO student){
        return "redirect:/";
    }
}
