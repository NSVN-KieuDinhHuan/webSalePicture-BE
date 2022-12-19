package com.newhouse.model.entity;

import com.newhouse.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "carts")
public class Cart {
    @Id
    private Long id;
    @OneToMany
    private List<CartDetail> CartDetailList;
}
