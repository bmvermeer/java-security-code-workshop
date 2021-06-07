package io.snyk.demo.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FileController {

    private static String UPLOADED_FOLDER = "upload";

    private static final Logger logger = LogManager.getLogger(UploadController.class);

    @GetMapping("/files")
    public String findAllFiles(Model model, @RequestParam(required = false) String folder) {
        String destination = UPLOADED_FOLDER;
        if (folder != null) {
            destination = folder;
        }
        model.addAttribute("folder", destination);
        model.addAttribute("files", getFileListing(destination));
        return "files";
    }


    private List getFileListing(String folder) {
        File dir = new File(folder);
        File[] files = dir.listFiles();

        return Arrays.stream(files)
                .map(File::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/file")
    public String index(Model model) {
        return "file";
    }

    @PostMapping("/file")
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "redirect:file";
        }

        //upload file
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "/" + file.getOriginalFilename());
            Files.write(path, bytes);
            redirectAttributes.addFlashAttribute("messages", Collections.singletonList("created file:" + path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/files";
    }
}
