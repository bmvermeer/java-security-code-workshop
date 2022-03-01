package io.snyk.demo.yaml;

import io.snyk.demo.domain.User;
import io.snyk.demo.repo.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class YamlProcessor {

    @Autowired
    UserRepo repo;

    private static final Logger logger = LogManager.getLogger(YamlProcessor.class);

    public List<String> parseYaml(File f) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(f);
        return parseYaml(inputStream);

    }

    public List<String> parseYaml(InputStream is) throws FileNotFoundException {
        Yaml yaml = new Yaml();
        HashMap lhm = (LinkedHashMap) yaml.load(is);
//        logger.info(lhm);
        ArrayList users = (ArrayList) lhm.get("user");
        return (List<String>) users.stream()
                .map(this::createUser)
                .collect(Collectors.toList());
    }

    private String createUser(Object hashmapUser) {
        HashMap hashmap = (LinkedHashMap) hashmapUser;
        User newEntry = new User(hashmap.get("firstname").toString()
                , hashmap.get("lastname").toString()
                , hashmap.get("username").toString()
                , hashmap.get("password").toString()
                , hashmap.get("comment").toString());
        return repo.save(newEntry).toString();
    }


}
