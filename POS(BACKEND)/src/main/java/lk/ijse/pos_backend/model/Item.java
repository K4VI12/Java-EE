package lk.ijse.pos_backend.model;

import lombok.Data;



@Data
public class Item {
    private String code;
    private String name;
    private int qty;
    private double price;
}
