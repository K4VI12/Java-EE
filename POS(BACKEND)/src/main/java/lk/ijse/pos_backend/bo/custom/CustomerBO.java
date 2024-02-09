package lk.ijse.pos_backend.bo.custom;

import lk.ijse.pos_backend.bo.SuperBO;
import lk.ijse.pos_backend.dto.CustomerDTO;
import lk.ijse.pos_backend.entity.CustomerEntity;

import java.sql.SQLException;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerEntity> GetAll() throws SQLException, ClassNotFoundException;
    Boolean SaveCustomer(CustomerDTO customerDTO);
    CustomerEntity getCustomer(String id);
    boolean UpdateCustomer(CustomerDTO customerDTO);
    boolean DeleteCustomer(String ID);
}
