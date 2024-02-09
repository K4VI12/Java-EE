package lk.ijse.pos_backend.dao.custom.impl.Util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TransactionUtil {
    public static <T>T execute(Connection connection,String sql, Object... args) throws SQLException, NamingException {
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                pstm.setObject((i + 1), args[i]);
            }

            if (sql.startsWith("SELECT") || sql.startsWith("select")) {
                return (T) pstm.executeQuery();
            } else {
                return (T) new Boolean(pstm.executeUpdate() > 0);
            }
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
