package lk.ijse.pos_backend.dao.custom.impl;

import lk.ijse.pos_backend.dao.custom.CustomerDao;
import lk.ijse.pos_backend.dao.custom.impl.Util.SqlUtil;
import lk.ijse.pos_backend.entity.CustomerEntity;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.hibernate.Session;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



@NoArgsConstructor
public class CustomerDaoImpl implements CustomerDao {
    private Session session;

    @SneakyThrows
    @Override
    public ArrayList<CustomerEntity> GetAll() {
        try (ResultSet rst = SqlUtil.execute("SELECT * FROM customer")) {
            ArrayList<CustomerEntity> data = new ArrayList<>();
            while (rst.next()) {
                data.add(new CustomerEntity(
                                rst.getString(1),
                                rst.getString(3),
                                rst.getString(2),
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
    public Boolean Save(CustomerEntity customerEntity) {
        return SqlUtil.execute("INSERT INTO customer(id, address, name, salary) VALUES(?, ?, ?, ?)",
                customerEntity.getId(),customerEntity.getAddress(),customerEntity.getName(),customerEntity.getSalary()
        );
    }

    @SneakyThrows
    @Override
    public CustomerEntity Search(String id) {
        ResultSet rst = SqlUtil.execute("SELECT * FROM customer WHERE id = ?",id);
        return rst.next() ? new CustomerEntity(
                rst.getString(1),
                rst.getString(3),
                rst.getString(2),
                rst.getDouble(4)
        ):null;
    }

    @SneakyThrows
    @Override
    public Boolean Update(CustomerEntity customerEntity) {
        return SqlUtil.execute("UPDATE customer SET address = ?, name = ?, salary = ? WHERE id  = ?",
                customerEntity.getAddress(),customerEntity.getName(),customerEntity.getSalary(),customerEntity.getId()
        );
    }

    @SneakyThrows
    @Override
    public Boolean Delete(String id) {
        return SqlUtil.execute("DELETE FROM customer WHERE  id = ?",id);
    }

    @Override
    public void SetSession(Session session) {
        this.session = session;
    }
}
