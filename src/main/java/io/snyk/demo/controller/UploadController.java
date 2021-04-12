package io.snyk.demo.controller;

import io.snyk.demo.xml.XmlProcessor;
import io.snyk.demo.yaml.YamlProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Controller
public class UploadController {

    @Autowired
    XmlProcessor xmlProcessor;

    @Autowired
    YamlProcessor yamlProcessor;

    private static final Logger logger = LogManager.getLogger(XmlProcessor.class);

    private static String UPLOADED_FOLDER = "upload/";

    @GetMapping("/upload")
    public String index(Model model) {
        return "upload";
    }

    @PostMapping("/upload")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:upload";
        }

        try {

           if (file.getContentType().contains("yaml")) {
                InputStream inputStream = file.getInputStream();
                List<String> messages = yamlProcessor.parseYaml(inputStream);
                redirectAttributes.addFlashAttribute("messages", messages);
            } else if (file.getContentType().equals("text/xml")) {
                // Get the file and save it somewhere
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
                Files.write(path, bytes);
                List<String> messages = xmlProcessor.parseXML(new File(UPLOADED_FOLDER + file.getOriginalFilename()));
                Files.delete(path);

                redirectAttributes.addFlashAttribute("messages", messages);

            } else {
                logger.error("not an XML or Yaml file!");
            }

        } catch (IOException e) {
            logger.error(e);
        }

        return "redirect:/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "uploadStatus";
    }

}
