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
    private String userPhone;
    private Date createDate;
    private int status;
    private List<OrderDetailDto> orderDetailList;

    public OrderListDto(String userPhone, Date createDate, int status, List<OrderDetailDto> orderDetailList) {
        this.userPhone = userPhone;
        this.createDate = createDate;
        this.status = status;
        this.orderDetailList = orderDetailList;
    }
}
