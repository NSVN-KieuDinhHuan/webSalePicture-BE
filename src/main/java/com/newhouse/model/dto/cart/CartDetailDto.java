package com.newhouse.model.dto.cart;

import com.newhouse.model.entity.dish.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto {
    private Long id;
    private Long dishId;
    private List<Long> productOption;
    private int quantity;
}
