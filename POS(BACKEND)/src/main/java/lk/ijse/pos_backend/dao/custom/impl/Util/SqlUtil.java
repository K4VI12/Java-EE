package lk.ijse.pos_backend.dao.custom.impl.Util;

import lk.ijse.pos_backend.dbresources.DBResources;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class SqlUtil {
    private static Connection con = null;

    public static <T>T execute(String sql, Object... args) throws SQLException, NamingException {
        try {
            con = DBResources.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);

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
        }finally {
            if (sql.startsWith("SELECT") || sql.startsWith("select")){
            }else {
                if(con!=null){
                    con.close();
                }
            }
        }
    }

    public static void CloseConnection(){
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
