package com.newhouse.repository;

import com.newhouse.model.entity.Option;
import com.newhouse.model.entity.OptionGroup;
import com.newhouse.model.entity.dish.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOptGroupRepository extends JpaRepository<OptionGroup, Long> {


}