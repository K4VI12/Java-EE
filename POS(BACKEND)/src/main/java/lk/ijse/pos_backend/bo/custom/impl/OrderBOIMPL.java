package lk.ijse.pos_backend.bo.custom.impl;

import lk.ijse.pos_backend.bo.custom.OrderBO;
import lk.ijse.pos_backend.dao.DaoFactory;
import lk.ijse.pos_backend.dao.custom.OrderDao;
import lk.ijse.pos_backend.dao.custom.OrderDetailsDao;
import lk.ijse.pos_backend.dbresources.DBResources;
import lk.ijse.pos_backend.dto.OrderDTO;
import lk.ijse.pos_backend.dto.OrderDetailsDTO;
import lk.ijse.pos_backend.entity.OrderEntity;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderBOIMPL implements OrderBO {

    Connection connection = null;
    OrderDao orderDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ORDER);
    OrderDetailsDao orderDetailsDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ORDERDETAILS);

    @Override
    public ArrayList<OrderEntity> GetAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Boolean SaveOrder(OrderDTO orderDTO, OrderDetailsDTO orderDetailsDTO) {
        try {
            connection = DBResources.getConnection();
            connection.setAutoCommit(false);

            orderDao.SetConnection(connection);
            orderDetailsDao.SetConnection(connection);
            boolean Save = orderDao.Save(orderDTO.ToEntity());
            if (Save){
                boolean bool = orderDetailsDao.Save(orderDetailsDTO.ToEntity());
                if(bool){
                    connection.commit();
                    return bool;
                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                return false;
            }
        } catch (SQLException|NamingException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(connection!=null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @Override
    public OrderEntity getOrder(String id) {
        return null;
    }

    @Override
    public boolean UpdateOrder(OrderDTO orderDTO, OrderDetailsDTO orderDetailsDTO) {
        return false;
    }

    @Override
    public boolean DeleteOrder(String OrderId) {
        try {
            connection = DBResources.getConnection();
            connection.setAutoCommit(false);

            orderDao.SetConnection(connection);
            orderDetailsDao.SetConnection(connection);
            boolean Delete = orderDetailsDao.Delete(OrderId);
            if (Delete){
                boolean bool = orderDao.Delete(OrderId);
                if(bool){
                    connection.commit();
                    return bool;
                }else {
                    connection.rollback();
                    return false;
                }
            }else {
                return false;
            }
        } catch (SQLException|NamingException e) {
            e.printStackTrace();
            return false;
        }finally {
            if(connection!=null){
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
