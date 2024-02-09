package lk.ijse.pos_backend.dto;

import lk.ijse.pos_backend.entity.CustomerEntity;
import lombok.Data;



@Data
public class CustomerDTO {
    private String Id;
    private String Name;
    private String Address;
    private double Salary;

    public CustomerEntity ToEntity(){
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(this.Id);
        customerEntity.setName(this.Name);
        customerEntity.setAddress(this.Address);
        customerEntity.setSalary(this.Salary);
        return customerEntity;
    }
}
