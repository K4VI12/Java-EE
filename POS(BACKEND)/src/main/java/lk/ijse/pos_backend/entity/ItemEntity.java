package lk.ijse.pos_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemEntity {
    private String code;
    private String name;
    private int qty;
    private double price;
}
