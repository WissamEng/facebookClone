package finalProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoSecurityController {

    @GetMapping("/")
    public String showHome(){
        return "home";
    }

    @GetMapping("/showLoginPage")
    public String showLoginPage(){
        //return "plain-login";
        return "fancy-login";
    }

    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }

    @GetMapping("/access-denied")
    public String showAccessDenied() {

        return "access-denied";
    }
}
