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
@Table(name = "Ordertbl")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long dishId;

    private int quantity;

    @ManyToOne
    private OrderGroup OrderGroup;

    @ManyToMany
    private List<Option> optionList;

    public Order(Long dishId, int quantity, List<Option> optionList) {
        this.dishId = dishId;
        this.quantity = quantity;
        this.optionList = optionList;
    }




}
