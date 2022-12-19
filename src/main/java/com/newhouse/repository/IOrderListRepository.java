package com.newhouse.repository;


import com.newhouse.model.entity.OrderList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderListRepository extends JpaRepository<OrderList, Long> {

}
