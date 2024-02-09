package lk.ijse.pos_backend.dbresources;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBResources {
    public static Connection getConnection() throws NamingException, SQLException {
        Context initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("java:comp/env/DBCP");

        return dataSource.getConnection();
    }
}
