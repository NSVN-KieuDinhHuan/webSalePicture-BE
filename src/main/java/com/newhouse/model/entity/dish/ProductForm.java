package com.newhouse.model.entity.dish;

import com.newhouse.model.entity.OptionGroup;
import com.newhouse.model.entity.dish.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForm {
    private Long id;

    private String name;

    private double price;

    private List<Category> categories;

    private  List<OptionGroup> optionGroups;

    private String description;

    private List<MultipartFile> image;

    private String specifications;
}