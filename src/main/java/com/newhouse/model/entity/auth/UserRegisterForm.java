package com.newhouse.model.entity.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterForm {
    @NotEmpty(message = "Không được để trống")
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @NotEmpty(message = "Không được để trống")
    @Column(unique = true)
    @Pattern(regexp = "^[0](\\\\+\\\\d{1,3}\\\\s?)?((\\\\(\\\\d{3}\\\\)\\\\s?)|(\\\\d{3})(\\\\s|-?))(\\\\d{3}(\\\\s|-?))(\\\\d{3})(\\\\s?(([E|e]xt[:|.|]?)|x|X)(\\\\s?\\\\d+))?")
    private String phone;
    private String password;
    private String confirmPassword;
    private String address;

    public boolean confirmPasswordMatch(){
        return password.equals(confirmPassword);
    }
}
