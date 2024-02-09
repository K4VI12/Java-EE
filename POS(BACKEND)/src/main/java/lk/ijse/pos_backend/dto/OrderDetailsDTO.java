package lk.ijse.pos_backend.dto;

import lk.ijse.pos_backend.entity.ItemEntity;
import lk.ijse.pos_backend.entity.OrderDetailsEntity;
import lk.ijse.pos_backend.entity.OrderEntity;
import lombok.Data;


@Data
public class OrderDetailsDTO {
    private String OrderDetailsID;
    private String ItemName;
    private int Quantity;
    private double UnitPrice;
    private String ItemID;
    private String OrderID;

    public OrderDetailsEntity ToEntity() {
        OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
        orderDetailsEntity.setOrderDetailsID(this.OrderDetailsID);
        orderDetailsEntity.setItemName(this.ItemName);
        orderDetailsEntity.setQuantity(this.Quantity);
        orderDetailsEntity.setUnitPrice(this.UnitPrice);
        orderDetailsEntity.setItemID(this.ItemID);
        orderDetailsEntity.setOrderID(this.OrderID);
        return orderDetailsEntity;
    }
}
