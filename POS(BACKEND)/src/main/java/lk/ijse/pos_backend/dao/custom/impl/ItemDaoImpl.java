package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.ItemDao;
import lk.ijse.pos_backend.dao.custom.impl.Util.SqlUtil;
import lk.ijse.pos_backend.entity.ItemEntity;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



@NoArgsConstructor
public class ItemDaoImpl implements ItemDao {
    private Session session;

    @SneakyThrows
    @Override
    public ArrayList<ItemEntity> GetAll() throws SQLException, ClassNotFoundException {
        try (ResultSet rst = SqlUtil.execute("SELECT * FROM item")) {
            ArrayList<ItemEntity> data = new ArrayList<>();
            while (rst.next()) {
                data.add(new ItemEntity(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getInt(3),
                                rst.getDouble(4)
                        )
                );
            }
            SqlUtil.CloseConnection();
            return data;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @SneakyThrows
    @Override
    public Boolean Save(ItemEntity itemEntity) {
        return SqlUtil.execute("INSERT INTO item(code, name, qty, price) VALUES(?, ?, ?, ?)",
                itemEntity.getCode(),itemEntity.getName(),itemEntity.getQty(),itemEntity.getPrice()
        );
    }

    @SneakyThrows
    @Override
    public ItemEntity Search(String itemcode) {
        ResultSet rst = SqlUtil.execute("SELECT * FROM item WHERE code = ?",itemcode);
        return rst.next() ? new ItemEntity(
                rst.getString(1),
                rst.getString(2),
                rst.getInt(3),
                rst.getDouble(4)
        ):null;
    }

    @SneakyThrows
    @Override
    public Boolean Update(ItemEntity itemEntity) {
        return SqlUtil.execute("UPDATE item SET name = ?, qty = ?, price = ? WHERE code  = ?",
                itemEntity.getName(),itemEntity.getQty(),itemEntity.getPrice(),itemEntity.getCode()
        );
    }

    @SneakyThrows
    @Override
    public Boolean Delete(String itemcode) {
        return SqlUtil.execute("DELETE FROM item WHERE  code = ?",itemcode);
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }
}
