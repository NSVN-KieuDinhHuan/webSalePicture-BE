package com.newhouse.model.entity.user;

import com.newhouse.model.entity.dish.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @Email
    @Column(unique = true)
    @NotBlank(message = "Không được để trống")
    private String email;

    @NotBlank(message = "Không được để trống")
    @Pattern(regexp = "^[0](\\\\+\\\\d{1,3}\\\\s?)?((\\\\(\\\\d{3}\\\\)\\\\s?)|(\\\\d{3})(\\\\s|-?))(\\\\d{3}(\\\\s|-?))(\\\\d{3})(\\\\s?(([E|e]xt[:|.|]?)|x|X)(\\\\s?\\\\d+))?")
    private String phone;

    @Column(columnDefinition = "varchar(255) default 'user-default.jpg'")
    private String image;

    @ManyToOne
    private Role role;

    private String fullName;

    private String address;
    private String password;
}
