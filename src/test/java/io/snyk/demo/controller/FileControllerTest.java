package io.snyk.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public class FileControllerTest {

    FileController controller = new FileController();


    @Test
    public void submitTest() {
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "hifolks.bar";
            }

            @Override
            public String getOriginalFilename() {
                return getName();
            }

            @Override
            public String getContentType() {
                return "text";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 1000;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File file) throws IOException, IllegalStateException {

            }
        };

        controller.singleFileUpload(file,new RedirectAttributesModelMap());
    }
}
