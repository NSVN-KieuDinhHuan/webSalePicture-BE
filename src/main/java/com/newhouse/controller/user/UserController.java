package com.newhouse.controller.user;

import com.newhouse.model.dto.order.OrderListDto;
import com.newhouse.model.entity.user.User;
import com.newhouse.model.entity.user.UserInfoForm;
import com.newhouse.service.order.IOrderListService;
import com.newhouse.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;



    @Autowired
    private IOrderListService orderService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (!user.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable Long id, @ModelAttribute UserInfoForm userInfoForm) {
        Optional<User> updateUserOptional = userService.findById(id);
        if (!updateUserOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User updateUser = updateUserOptional.get();
        updateUser.setUsername(userInfoForm.getUsername());
        updateUser.setEmail(userInfoForm.getEmail());
        updateUser.setPhone(userInfoForm.getPhone());
        updateUser.setFullName(userInfoForm.getFullName());
        updateUser.setAddress(userInfoForm.getAddress());

        MultipartFile img = userInfoForm.getImage();
        if (img != null && img.getSize() != 0) {
            String fileName = img.getOriginalFilename();
            long currentTime = System.currentTimeMillis();
            fileName = currentTime + "_" + fileName;
            try {
                FileCopyUtils.copy(img.getBytes(), new File(uploadPath + fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateUser.setImage(fileName);
        }
        return new ResponseEntity<>(userService.save(updateUser), HttpStatus.OK);
    }





}
