package io.snyk.demo.yaml;

import io.snyk.demo.domain.User;
import io.snyk.demo.repo.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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
        List<String> messages = new ArrayList<>();
        Yaml yaml = new Yaml(new Constructor(User.class));

        for (Object object : yaml.loadAll(is)) {
            if (object instanceof User) {
                User newUser = (User) object;
                repo.save(newUser);
                messages.add("created user " + newUser);
            } else {
                logger.error("not a valid user ?", object);
                messages.add("not a valid user: " + object);
            }
        }

        return messages;
    }

    public void parseYaml1(File f) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(f);
        Yaml yaml = new Yaml();
        LinkedHashMap lhm = (LinkedHashMap) yaml.load(inputStream);
        lhm.values().forEach(v -> System.out.println(v.getClass()));
    }

    public void parseYaml2(File f) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(f);
        Yaml yaml = new Yaml(new Constructor(User.class));

        for (Object object : yaml.loadAll(inputStream)) {
            User newUser = (User) object;
            System.out.println(newUser);

        }

    }

    public void parseYaml3(File f) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(f);
        Yaml yaml = new Yaml(new Constructor(Group.class));
        Group group = yaml.load(inputStream);
        group.getPeople().forEach(System.out::println);
    }


    public static void main(String[] args) throws FileNotFoundException {
        YamlProcessor yp = new YamlProcessor();


//        File f = new File("/Users/brianvermeer/demo/snyk/java-code-workshop/examples/users.yml");
//        yp.parseYaml2(f);

        File fbatch = new File("/Users/brianvermeer/demo/snyk/java-code-workshop/examples/group.yml");
        yp.parseYaml3(fbatch);

//        File f = new File("/Users/brianvermeer/demo/snyk/java-code-workshop/examples/laughs.yml");
//        yp.parseYaml1(f);



    }



}
