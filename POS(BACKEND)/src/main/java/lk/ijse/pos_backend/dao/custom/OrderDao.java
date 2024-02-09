package lk.ijse.pos_backend.dao.custom;

import lk.ijse.pos_backend.dao.CrudDao;
import lk.ijse.pos_backend.entity.OrderEntity;
import org.hibernate.Session;

import java.sql.Connection;


public interface OrderDao extends CrudDao<OrderEntity,String> {
    void SetSession(Session session);
    void SetConnection(Connection connection);
}
