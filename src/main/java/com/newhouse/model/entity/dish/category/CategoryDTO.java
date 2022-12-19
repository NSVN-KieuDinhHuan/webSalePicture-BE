package com.newhouse.model.entity.dish.category;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    private String name;
    private int numberOfDishes;
}
