package com.newhouse.model.entity.dish;

import com.newhouse.model.entity.ProductOptionList;
import com.newhouse.model.entity.dish.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.ManyToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishForm {
    private Long id;

    private String name;

    private double price;

    private List<Category> categories;

    private  List<ProductOptionList> OptionOfProduct;

    private String description;

    private List<MultipartFile> image;

    private String specifications;
}