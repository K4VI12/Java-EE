package lk.ijse.pos_backend.bo.custom;

import lk.ijse.pos_backend.bo.SuperBO;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.entity.ItemEntity;

import java.sql.SQLException;
import java.util.ArrayList;


public interface ItemBO extends SuperBO {
    ArrayList<ItemEntity> GetAll() throws SQLException, ClassNotFoundException;
    Boolean SaveItem(ItemDTO itemDTO);
    ItemEntity getItem(String itemCode);
    boolean UpdateItem(ItemDTO itemDTO);
    boolean DeleteItem(String itemCode);
}
