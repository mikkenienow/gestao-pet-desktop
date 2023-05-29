package gestaopet.DB;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class DBManager {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    private static ArrayList<String> insertList = new ArrayList<String>();
    
    
    
    public DBManager(){
        con = DbConnection.ConnectionDB();
        //String[] list = {"teste", "outro", "alguma coisa", "funciona"};
        //getList(list);
    }
    
    public static void start(){
        con = DbConnection.ConnectionDB();
    }
    /*
    public static void insert(String table, String[] values){
        try {
            String sql = "INSERT INTO " + getStatement(0, table) + " VALUES (" + getList(values) + ");";
            pst = con.prepareStatement(sql);

            for(int i = 0; i < insertList.size(); i++){
                System.out.println("Detecção do erro" + i);
                pst.setString(i+1, insertList.get(i));
            }
            pst.execute();
            System.out.println("Inserido com sucesso!");
            
            insertList.clear();
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }*/
    

    
    /*
    private static String getList(String[] values){
        String list = "";
        for(int i = 0; i < values.length; i++){
            list = list + "?,";
            insertList.add(values[i]);
        }
        list = list.substring(0, list.length() - 1);
        System.out.println("lista: " + insertList);
        return list;
    }*/
    

}
