package lk.ijse.pos_backend.dao.custom;

import lk.ijse.pos_backend.dao.CrudDao;
import lk.ijse.pos_backend.entity.ItemEntity;
import org.hibernate.Session;


public interface ItemDao extends CrudDao<ItemEntity,String> {
    void SetSession(Session session);
}
