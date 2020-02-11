package io.snyk.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PasswordDto {
    private String username;
    private String password;
}
