package rovoq19.contacts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import rovoq19.contacts.domain.User;
import rovoq19.contacts.reps.ContactRep;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {
    private final ContactRep contactRep;

    @Autowired
    public MainController (ContactRep contactRep) {
        this.contactRep = contactRep;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap <Object, Object> data = new HashMap <> ();
        data.put ("profile", user);
        data.put ("contacts", contactRep.findAll ());
        model.addAttribute ("frontendData", data);
        return "index";
    }
}
