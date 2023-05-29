package gestaopet.DB;


import gestaopet.classes.DateTools;
import gestaopet.classes.Pet;
import gestaopet.classes.Vacina;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class VacinaDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    
    
    
    public VacinaDB(){
        con = DbConnection.ConnectionDB();

    }
    
    public static void connect(boolean connect){
        if(connect){
            con = DbConnection.ConnectionDB();
        } else {
            try {
                if(!con.isClosed()){
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e);
            }
        }
    }
    
    public static void insert(Vacina vacina){
        connect(true);
        String[] values = vacina.getValues();
        try {
            String sql = "INSERT INTO vacina ("
                    + "nome, "
                    + "petid, "
                    + "validade)"
                    + " VALUES ("
                    + "?, "
                    + "?, "
                    + "?);";

            pst = con.prepareStatement(sql);

            for(int i = 0; i < values.length; i++){
                pst.setString(i+1, values[i]);
            }
                pst.execute();
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public static void delete(int id){
        connect(true);

        try {
            String sql = "DELETE FROM vacina WHERE idvacina =" + id;

            pst = con.prepareStatement(sql);
            pst.execute();
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    
    public static List<Vacina> getAllByID(int idpet){
        connect(true);
        List<Vacina> output = new ArrayList<>();
        try {
            String sql =    "SELECT * FROM vacina WHERE petid=" + idpet + " ORDER BY idvacina DESC";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                String data = "";
                try {
                    
                    if(rs.getString("validade").equals("")){
                        data = DateTools.dateToString(DateTools.today);
                    } else {
                        data = DateTools.dateToString(DateTools.SQLToDate(rs.getString("validade"), "00:00"));
                    }
                } catch (Exception e) {
                }

                output.add(new Vacina(
                        rs.getInt("idvacina"),
                        rs.getString("nome"),
                        rs.getInt("petid"),
                        data
                ));
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return output;
    }

}