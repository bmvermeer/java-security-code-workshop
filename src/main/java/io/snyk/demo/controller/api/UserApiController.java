package io.snyk.demo.controller.api;

import io.snyk.demo.domain.User;
import io.snyk.demo.repo.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserApiController {

    @Autowired
    SearchRepo searchRepo;


    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> allUsers(Model model) {
        return searchRepo.findAllUsers();
    }

}
