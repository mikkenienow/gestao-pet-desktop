
package gestaopet.DB;

import static gestaopet.DB.CanilDB.connect;
import gestaopet.classes.Alojamento;
import gestaopet.classes.Canil;
import gestaopet.classes.DateTools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AlojamentoDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    
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
    
    static public List<Alojamento> getByCanil(int canilId){
        connect(true);
        List<Alojamento> output = new ArrayList<>();
        try {
            String sql = "SELECT * FROM alojamento WHERE idcanil="+ canilId +"";
            

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Alojamento(
                        rs.getInt("idalojamento"),
                        rs.getString("titulo")
                ));
            }

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return output;
    }
//    INSERT INTO alojamento (;
//                           idalojamento,
//                           titulo,
//                           idcanil
//                       )
//                       VALUES (
//                           'idalojamento',
//                           'titulo',
//                           'idcanil'
//                       );

    
    static public void add(Canil canil){
        connect(true);
        
        try {
            String sql = "INSERT INTO alojamento (titulo, idcanil) VALUES (?,?)";

            
            
            pst = con.prepareStatement(sql);
            for(int i = 0; i < canil.getTotalSections(); i++){
                pst.setString(1, canil.getAlojamento(i).getTitulo());
                pst.setInt(2, canil.getId());
                pst.execute();
            }

            pst.close();

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    static public List<Alojamento> getByDateCanil(int canilId, Date i, Date f){
        connect(true);
        List<Alojamento> output = new ArrayList<>();
        try {
            String di = DateTools.dateToSQL(i);
            String df = DateTools.dateToSQL(f);
            String sql = "SELECT * FROM alojamento WHERE idcanil = " + canilId + " AND idalojamento NOT IN (SELECT  idalojamento FROM reserva WHERE (checkin <= '"+ df +"' AND checkout >= '"+ di +"'))";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Alojamento(
                        rs.getInt("idalojamento"),
                        rs.getString("titulo")
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
