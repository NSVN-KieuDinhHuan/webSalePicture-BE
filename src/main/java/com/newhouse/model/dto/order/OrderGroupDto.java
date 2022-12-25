package com.newhouse.model.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderGroupDto {
    private Long id;
    private String userPhone;
    private Date createDate;
    private int status;


    public OrderGroupDto(String userPhone, Date createDate, int status) {
        this.userPhone = userPhone;
        this.createDate = createDate;
        this.status = status;

    }
}
