package com.newhouse.model.dto.order;

import com.newhouse.model.entity.OrderGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private Long dishId;
    private List<Long> productOption;
    private int quantity;
    private Long OrderGroupId;
}
