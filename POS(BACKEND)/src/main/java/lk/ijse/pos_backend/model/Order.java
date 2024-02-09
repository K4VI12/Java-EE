package lk.ijse.pos_backend.model;

import lombok.Data;


import java.util.Date;

@Data
public class Order {
    private String orderId;
    private String date;
    private String customerId;
    private String orderDetailsId;
    private String itemName;
    private int qty;
    private double unitPrice;
    private String itemId;
}
