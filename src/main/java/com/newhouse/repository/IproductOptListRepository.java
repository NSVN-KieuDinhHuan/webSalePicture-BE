package com.newhouse.repository;

import com.newhouse.model.entity.ProductOption;
import com.newhouse.model.entity.ProductOptionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IproductOptListRepository extends JpaRepository<ProductOptionList, Long> {

}