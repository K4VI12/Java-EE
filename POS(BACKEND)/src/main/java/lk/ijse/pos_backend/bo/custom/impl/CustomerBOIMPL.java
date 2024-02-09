package lk.ijse.pos_backend.bo.custom.impl;

import lk.ijse.pos_backend.bo.custom.CustomerBO;
import lk.ijse.pos_backend.dao.DaoFactory;
import lk.ijse.pos_backend.dao.custom.CustomerDao;
import lk.ijse.pos_backend.dto.CustomerDTO;
import lk.ijse.pos_backend.entity.CustomerEntity;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;


public class CustomerBOIMPL implements CustomerBO {

    CustomerDao customerDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.CUSTOMER);

    @Override
    public ArrayList<CustomerEntity> GetAll() throws SQLException, ClassNotFoundException {
        return customerDao.GetAll();
    }

    @Override
    public Boolean SaveCustomer(CustomerDTO customerDTO) {
        return customerDao.Save(customerDTO.ToEntity());
    }

    @SneakyThrows
    @Override
    public CustomerEntity getCustomer(String id) {
        return customerDao.Search(id);
    }

    @Override
    public boolean UpdateCustomer(CustomerDTO customerDTO) {
        return customerDao.Update(customerDTO.ToEntity());
    }

    @Override
    public boolean DeleteCustomer(String ID) {
        return customerDao.Delete(ID);
    }
}
