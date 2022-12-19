package com.newhouse.model.dto.order;

import com.newhouse.model.entity.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderListDto {
    private Long id;
    private Long userId;
    private Date createDate;
    private int status;
    private List<Long> orderDetailList;

    public OrderListDto(Long id, Long userId, Date createDate, int status, List<Long> orderDetailList) {
        this.id = id;
        this.userId = userId;
        this.createDate = createDate;
        this.status = status;
        this.orderDetailList = orderDetailList;
    }
}
