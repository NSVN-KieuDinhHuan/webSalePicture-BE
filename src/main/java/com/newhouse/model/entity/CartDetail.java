package com.newhouse.model.entity;

import com.newhouse.model.entity.dish.Dish;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cartDetail")
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dishId;

    private int quantity;

    @ManyToMany
    private List<ProductOption> productOptions;

    public CartDetail(Long dishId, int quantity, List<ProductOption> productOptions) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.productOptions = productOptions;
    }

}
