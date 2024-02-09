package lk.ijse.pos_backend.dto;

import lk.ijse.pos_backend.entity.CustomerEntity;
import lk.ijse.pos_backend.entity.OrderEntity;
import lombok.Data;

import java.util.Date;


@Data
public class OrderDTO {
    private String OrderId;
    private String CustomerId;
    private Date date;

    public OrderEntity ToEntity(){
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrder_id(this.OrderId);
        orderEntity.setCustomerId(this.CustomerId);
        orderEntity.setDate(this.date);
        return orderEntity;
    }
}
