package com.newhouse.repository;

import com.newhouse.model.entity.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IproductOptRepository extends JpaRepository<ProductOption, Long> {

}