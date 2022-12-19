package com.newhouse.model.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class OrderDetailDto {
    private Long id;
    private Long dishId;
    private List<Long> productOption;
    private int quantity;
}
