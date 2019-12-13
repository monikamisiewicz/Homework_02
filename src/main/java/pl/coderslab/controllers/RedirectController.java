package pl.coderslab.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RedirectController {

    //2.2 Utwórz akcję dostępną pod adresem /first. Akcja ma ma wyświetlać widok first.jsp
    //2.4 W widoku first.jsp utwórz link do akcji dostępnej pod adresem /second,
    // akcja ma przekierowywać do akcji dostępnej pod adresem /third.

    @RequestMapping("/first")
    public String first() {
        return "first";
    }

    @RequestMapping("/second")
    public String second(HttpServletRequest request) {
        return "redirect:third";
    }


    //2.3 Utwórz akcję dostępną pod adresem /third. Akcja ma ma wyświetlać widok third.jsp

    @RequestMapping("/third")
    public String third() {
        return "third";
    }

}
