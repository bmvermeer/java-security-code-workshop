package io.snyk.demo.xml;


import io.snyk.demo.domain.User;
import io.snyk.demo.repo.UserRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
public class XmlProcessorDom4J {

    @Autowired
    UserRepo userRepo;

    private List<String> messages = new ArrayList<>();
    private static final Logger logger = LogManager.getLogger(XmlProcessorDom4J.class);

    public List<String> parseXML(InputStream is) {
        messages.clear();
        try {
            SAXReader reader = new SAXReader();
            Document document = reader.read(is);

            Element rootElement = document.getRootElement();
            List<Element> elements = rootElement.elements();

            for (Element user : elements) {
                User newUser = new User(user.elementText("firstname"),
                        user.elementText("lastname"),
                        user.elementText("username"),
                        user.elementText("password"),
                        user.elementText("comment"));
                userRepo.save(newUser);

                messages.add("created user " + newUser);
            }

        } catch (Exception e) {
            messages.add(e.getMessage());
            logger.error(e);
        }
        return messages;
    }
}
