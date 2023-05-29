package gestaopet.DB;

import java.sql.*;

public class DbConnection {
    
    public static Connection ConnectionDB(){
        
        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection("jdbc:sqlite:LocalDB.db");
            return con;
        } catch (Exception e) {
            System.out.println("Falha na conexão: " + e);
            return null;
        }
    }
    
}
