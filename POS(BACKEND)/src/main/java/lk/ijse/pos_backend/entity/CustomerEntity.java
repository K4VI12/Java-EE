package lk.ijse.pos_backend.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity {
    private String id;
    private String name;
    private String address;
    private double salary;
}
