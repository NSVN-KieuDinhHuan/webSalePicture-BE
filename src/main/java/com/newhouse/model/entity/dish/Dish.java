package com.newhouse.model.entity.dish;

import com.newhouse.model.entity.ProductOption;
import com.newhouse.model.entity.ProductOptionList;
import com.newhouse.model.entity.dish.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "dishes")
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(100)", nullable = false) // Xử lý ở tầng Data Layer
    @NotNull
    private String name;

    @Column(nullable = false)
    @NotNull
    private double price;

    @ManyToMany
    private List<Category> categories;

    @ManyToMany
    private List<ProductOptionList> OptionOfProduct;

    @Column (columnDefinition = "BIGINT default 0")
    private Long sold;

    @Column(columnDefinition = "varchar(1000)")
    private String description;

    @Column(columnDefinition = "varchar(1000)")
    private String specifications;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image01;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image02;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image03;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image04;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image05;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image06;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image07;


    @Column(columnDefinition = "varchar(1000) default 'dish-default.jpg'")
    private String image08;

}