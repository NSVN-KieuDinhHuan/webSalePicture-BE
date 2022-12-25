package com.newhouse.repository;


import com.newhouse.model.entity.OrderGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderGroupRepository extends JpaRepository<OrderGroup, Long> {

}
