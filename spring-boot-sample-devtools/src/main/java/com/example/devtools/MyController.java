package com.example.devtools;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class MyController {

    @PostConstruct
    public void slowRestart() throws InterruptedException {
        Thread.sleep(5000);
    }

    @GetMapping("/")
    public ModelAndView get(HttpSession session) {
        Object sessionVar = session.getAttribute("var");
        if (sessionVar == null) {
            sessionVar = new Date();
            session.setAttribute("var", sessionVar);
        }
        ModelMap model = new ModelMap("message", Message.MESSAGE).addAttribute("sessionVar", sessionVar);
        return new ModelAndView("hello", model);
    }

}
