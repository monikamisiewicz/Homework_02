package pl.coderslab.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//1.1 Stwórz kontroler o nazwie HeaderController.

@Controller
public class HeaderController {
    private final Logger logger = LoggerFactory.getLogger(HeaderController.class);

    //1.2 Stwórz akcję dostępną pod adresem /showUserAgent
    //1.3 Pobierz wartość nagłówka userAgent

    //I - w przeglądarce
//    @RequestMapping("/showUserAgent")
//    @ResponseBody
//    public String showUserAgent(@RequestHeader("user-agent") String userAgent) {
//        return "user-agent = " + userAgent;
//    }

    //II - widok w pliku jsp - http://localhost:8080/showUserAgent/user-agent
//    @RequestMapping("/showUserAgent/{userAgent}")
//    public String showUserAgent(@PathVariable String userAgent) {
//        return "userAgent";
//    }

    //III - zalecany sposób przekazywania parametrów do widoku to wykorzystanie obiektu klasy Model z pakietu: org.springframework.ui.Model
    @RequestMapping("/showUserAgent/{userAgent}")
    public String showUserAgent(Model model, @PathVariable String userAgent) {
        model.addAttribute("userAgent", userAgent);
        return "userAgent";
    }

}
