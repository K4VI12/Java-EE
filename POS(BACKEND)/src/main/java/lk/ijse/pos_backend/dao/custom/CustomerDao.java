package lk.ijse.pos_backend.dao.custom;

import lk.ijse.pos_backend.dao.CrudDao;
import lk.ijse.pos_backend.entity.CustomerEntity;
import org.hibernate.Session;


public interface CustomerDao extends CrudDao<CustomerEntity,String> {
    void SetSession(Session session);
}
