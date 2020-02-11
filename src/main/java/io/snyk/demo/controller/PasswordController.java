package io.snyk.demo.controller;

import io.snyk.demo.dto.PasswordDto;
import io.snyk.demo.password.PasswordUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {

    @Autowired
    PasswordUtil pwChecker;


    private static final Logger logger = LogManager.getLogger(PasswordController.class);

    @GetMapping("/testpw")
    public String getPage(Model model, @RequestParam("id") String username) {
        PasswordDto pwdto = new PasswordDto();
        pwdto.setUsername(username);

        model.addAttribute("passworddto", pwdto);
        return "testpw";
    }

    @PostMapping("/testpw")
    public String testPassword(Model model, @ModelAttribute PasswordDto pwdto) {
        logger.info("testpassword {}", pwdto);
        boolean check = pwChecker.check(pwdto.getUsername(), pwdto.getPassword());
        model.addAttribute("message", check);
        model.addAttribute("passworddto", pwdto);
        return "testpw";
    }



}
