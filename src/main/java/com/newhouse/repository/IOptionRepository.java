package com.newhouse.repository;

import com.newhouse.model.entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IOptionRepository extends JpaRepository<Option, Long> {
    @Query(value = "select option.* from option " +
            "where group_id= :optionGroupId", nativeQuery = true)
    Iterable<Option> getOptionByOptionGroup(
            @Param(value = "optionGroupId") int optionGroupId);
}