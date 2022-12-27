package com.newhouse.controller.auth;

import com.newhouse.model.entity.auth.ErrorMessage;
import com.newhouse.model.entity.auth.JwtResponse;
import com.newhouse.model.entity.auth.UserRegisterForm;
import com.newhouse.model.entity.user.Role;
import com.newhouse.model.entity.user.User;
import com.newhouse.service.auth.JwtService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    IUserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @PostMapping ("/register")
    public ResponseEntity<?> register (@RequestBody UserRegisterForm userRegisterForm){
        User user = new User();
        String phone=userRegisterForm.getPhone();
        Optional<User> findUserByPhone = userService.findByPhone(phone);
        if (findUserByPhone.isPresent()) {
            user =findUserByPhone.get();
        }
        if(userRegisterForm.getPassword()!=null) {
            if (!userRegisterForm.confirmPasswordMatch()) {
                ErrorMessage errorMessage = new ErrorMessage("Mật khẩu nhập lại không khớp!");
                return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
            }
            String encodedPassword = passwordEncoder.encode(userRegisterForm.getPassword());
            user.setPassword(encodedPassword);
        }
        Optional<User> findUser = userService.findByPhone(userRegisterForm.getPhone());
        if (findUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("số điện thoại đã tồn tại!");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Optional<User> checkmail = userService.findByEmail(userRegisterForm.getEmail());
        if (checkmail.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("mail đã đã tồn tại!");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }


        user.setEmail(userRegisterForm.getEmail());
        user.setUsername(userRegisterForm.getUsername());
        user.setAddress(userRegisterForm.getAddress());
        user.setPhone(userRegisterForm.getPhone());

        Role role = new Role(2L, Role.ROLE_CUSTOMER);
        user.setImage("user-default.jpg");
        user.setRole(role);
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        String inputEmail = user.getEmail();
        String inputPassword = user.getPassword();
        Optional<User> findUser = userService.findByEmail(inputEmail);

        if (!findUser.isPresent()) {
            ErrorMessage errorMessage = new ErrorMessage("Tài khoản không tồn tại");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        boolean matchPassword = passwordEncoder.matches(inputPassword, findUser.get().getPassword());
        if (!matchPassword) {
            ErrorMessage errorMessage = new ErrorMessage("Mật khẩu không đúng");
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(findUser.get().getUsername(), inputPassword)); // tạo đối tượng Authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);  // lưu đối tượng Authentication vào ContextHolder
        String jwt = jwtService.generateTokenLogin(authentication);  // tạo token từ đối tượng Authentication
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = findUser.get();

        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setId(currentUser.getId());
        jwtResponse.setToken(jwt);
        jwtResponse.setUsername(userDetails.getUsername());
        jwtResponse.setRoles(userDetails.getAuthorities());
        jwtResponse.setEmail(inputEmail);
        return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
    }
}
