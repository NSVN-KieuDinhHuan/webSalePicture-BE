package com.newhouse.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderDetail")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dishId;

    private int quantity;

    @ManyToMany
    private List<ProductOption> optionList;

    public OrderDetail(Long dishId, int quantity, List<ProductOption> optionList) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.optionList = optionList;
    }




}
