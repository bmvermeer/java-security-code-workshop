package io.snyk.demo.password;

import io.snyk.demo.domain.User;
import io.snyk.demo.repo.SearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PasswordUtil {

    @Autowired
    SearchRepo searchRepo;
    public boolean check(String username, String password) {

        List<User> users = searchRepo.findUsersByUsername(username);
        if (!users.isEmpty()) {
            return users.get(0).getPassword().equals(password);

        }
        return false;
    }



}
