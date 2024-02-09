package lk.ijse.pos_backend.dao;

import java.sql.SQLException;
import java.util.ArrayList;


public interface CrudDao<T,Type>extends SuperDao {
    ArrayList<T> GetAll() throws SQLException, ClassNotFoundException;
    Boolean Save(T t);
    T Search(Type type) throws SQLException, ClassNotFoundException;
    Boolean Update(T t);
    Boolean Delete(Type type);
}
