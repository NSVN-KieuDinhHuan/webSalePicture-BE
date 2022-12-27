package com.newhouse.model.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoForm {
    private String username;
    @Email
    @NotEmpty(message = "Không được để trống")
    private String email;

    @NotEmpty(message = "Không được để trống")
    @Pattern(regexp = "^[0](\\\\+\\\\d{1,3}\\\\s?)?((\\\\(\\\\d{3}\\\\)\\\\s?)|(\\\\d{3})(\\\\s|-?))(\\\\d{3}(\\\\s|-?))(\\\\d{3})(\\\\s?(([E|e]xt[:|.|]?)|x|X)(\\\\s?\\\\d+))?")
    private String phone;

    private MultipartFile image;

    private Role role;

    private String fullName;

    private String address;
    private String password;
}
