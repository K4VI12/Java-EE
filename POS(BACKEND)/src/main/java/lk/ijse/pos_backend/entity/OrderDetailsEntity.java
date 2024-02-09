package lk.ijse.pos_backend.entity;

import lombok.*;

import javax.persistence.*;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailsEntity {
    private String OrderDetailsID;
    private String ItemName;
    private int Quantity;
    private double UnitPrice;
    private String ItemID;
    private String OrderID;
}
