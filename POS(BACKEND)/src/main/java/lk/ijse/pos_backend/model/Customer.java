package lk.ijse.pos_backend.model;

import lombok.*;


@Data
public class Customer {
    private String id;
    private String name;
    private String address;
    private double salary;
}
