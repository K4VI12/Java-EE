package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.OrderDetailsDao;
import lk.ijse.pos_backend.dao.custom.impl.Util.SqlUtil;
import lk.ijse.pos_backend.dao.custom.impl.Util.TransactionUtil;
import lk.ijse.pos_backend.entity.OrderDetailsEntity;
import lk.ijse.pos_backend.entity.OrderEntity;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


@NoArgsConstructor
public class OrderDetailsDaoImpl implements OrderDetailsDao {
    private Session session;
    private Connection connection = null;

    @SneakyThrows
    @Override
    public ArrayList<OrderDetailsEntity> GetAll() throws SQLException, ClassNotFoundException {
        try (ResultSet rst = SqlUtil.execute("SELECT * FROM order_details")) {
            ArrayList<OrderDetailsEntity> data = new ArrayList<>();
            while (rst.next()) {
                data.add(new OrderDetailsEntity(
                                rst.getString(1),
                                rst.getString(2),
                                rst.getInt(3),
                                rst.getDouble(4),
                                rst.getString(5),
                                rst.getString(6)
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
    public Boolean Save(OrderDetailsEntity orderDetailsEntity) {
        return TransactionUtil.execute(this.connection,"INSERT INTO order_details(order_details_id, item_name, qty, unit_price, item_id, order_id) VALUES(?, ?, ?, ?, ?, ?)",
                orderDetailsEntity.getOrderDetailsID(),orderDetailsEntity.getItemName(),orderDetailsEntity.getQuantity(),
                orderDetailsEntity.getUnitPrice(),orderDetailsEntity.getItemID(),orderDetailsEntity.getOrderID()
        );
    }

    @SneakyThrows
    @Override
    public OrderDetailsEntity Search(String id) {
        ResultSet rst = SqlUtil.execute("SELECT * FROM order_details WHERE order_details_id = ?",id);
        return rst.next() ? new OrderDetailsEntity(
                rst.getString(1),
                rst.getString(2),
                rst.getInt(3),
                rst.getDouble(4),
                rst.getString(5),
                rst.getString(6)
        ):null;
    }

    @SneakyThrows
    @Override
    public Boolean Update(OrderDetailsEntity orderDetailsEntity) {
        return TransactionUtil.execute(this.connection,"UPDATE order_details SET item_name = ?, qty = ?, unit_price = ?, item_id = ?, order_id = ? WHERE order_details_id  = ?",
                orderDetailsEntity.getItemName(),orderDetailsEntity.getQuantity(),orderDetailsEntity.getUnitPrice(),
                orderDetailsEntity.getItemID(),orderDetailsEntity.getOrderID(),orderDetailsEntity.getOrderDetailsID()
        );
    }

    @SneakyThrows
    @Override
    public Boolean Delete(String orderdetailsid) {
        return TransactionUtil.execute(this.connection,"DELETE FROM order_details WHERE  order_details_id = ?",orderdetailsid);
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }

    @Override
    public void SetConnection(Connection connection) {
        this.connection = connection;
    }
}
