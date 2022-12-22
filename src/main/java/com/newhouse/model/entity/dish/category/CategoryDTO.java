package com.newhouse.model.entity.dish.category;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<MultipartFile> image;
    private int numberOfDishes;
}
