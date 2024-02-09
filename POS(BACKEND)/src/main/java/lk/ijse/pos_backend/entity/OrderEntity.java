package lk.ijse.pos_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    private String order_id;
    private Date date;
    private String customerId;
}
