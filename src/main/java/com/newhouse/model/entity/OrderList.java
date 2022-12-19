package com.newhouse.model.entity;

import com.newhouse.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orderList")
public class OrderList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    private Date createDate;

    @OneToMany
    private List<OrderDetail> orderDetailList;

    @Column(columnDefinition = "TINYINT default 0")
    private int status;

}
