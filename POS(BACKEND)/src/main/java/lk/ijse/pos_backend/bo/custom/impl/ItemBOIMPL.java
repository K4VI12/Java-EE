package lk.ijse.pos_backend.bo.custom.impl;

import lk.ijse.pos_backend.bo.custom.ItemBO;
import lk.ijse.pos_backend.dao.DaoFactory;
import lk.ijse.pos_backend.dao.custom.ItemDao;
import lk.ijse.pos_backend.dto.ItemDTO;
import lk.ijse.pos_backend.entity.ItemEntity;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.ArrayList;


public class ItemBOIMPL implements ItemBO {

    ItemDao itemDao = DaoFactory.getDaoFactory().getDao(DaoFactory.DaoType.ITEM);

    @Override
    public ArrayList<ItemEntity> GetAll() throws SQLException, ClassNotFoundException {
        return itemDao.GetAll();
    }

    @Override
    public Boolean SaveItem(ItemDTO itemDTO) {
        return itemDao.Save(itemDTO.ToEntity());
    }

    @SneakyThrows
    @Override
    public ItemEntity getItem(String itemCode) {
        return itemDao.Search(itemCode);
    }

    @Override
    public boolean UpdateItem(ItemDTO itemDTO) {
        return itemDao.Update(itemDTO.ToEntity());
    }

    @Override
    public boolean DeleteItem(String itemCode) {
        return itemDao.Delete(itemCode);
    }
}
