
package gestaopet.DB;

import static gestaopet.DB.PessoaDB.connect;
import gestaopet.classes.DateTools;
import gestaopet.classes.Canil;
import gestaopet.enums.PetSize;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CanilDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    
    public static void connect(boolean connect){
        if(connect){
            con = DbConnection.ConnectionDB();
        } else {
            try {
                    con.close();

            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e);
            }
        }
    }


    static public List<Canil> getByPorte(PetSize tamanho){
        connect(true);
        List<Canil> output = new ArrayList<>();
        try {
            String sql =(tamanho.equals(PetSize.Grande))?    "SELECT * FROM canil WHERE porte='"+tamanho+"'" : "SELECT * FROM canil";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add(new Canil(
                        rs.getInt("idcanil"),
                        rs.getString("titulo"),
                        rs.getString("porte")
                ));
            }

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
                
                
                
        return output;
    }
    
    //    INSERT INTO canil (;
    //                      idcanil,
    //                      titulo,
    //                      porte
    //                  )
    //                  VALUES (
    //                      'idcanil',
    //                      'titulo',
    //                      'porte'
    //                  );
    
    static public void add(Canil canil){
        
        
        try {
            getCanilByTitulo(canil.getTitulo()).getId();
        } catch (Exception e) {
            connect(true);
            try {
                String sql = "INSERT INTO canil (titulo, porte) VALUES (?,?)";

                pst = con.prepareStatement(sql);
                pst.setString(1, canil.getTitulo());
                pst.setString(2, canil.getPorte() + "");

                pst.execute();
                pst.close();

                connect(false);
                pst = null;
            } catch (Exception j) {
                System.out.println("Add Erro: " + j);
            }
            canil.setId(getCanilByTitulo(canil.getTitulo()).getId());

            AlojamentoDB.add(canil);
        }
    }
    
    static public void delete(int idcanil){
        connect(true);

        try {
            String sql = "DELETE FROM canil WHERE idcanil =" + idcanil;
            String sql2 = "DELETE FROM alojamento WHERE idcanil =" + idcanil;
            pst = con.prepareStatement(sql);

            pst.execute();
            pst.close();
            
            pst = con.prepareStatement(sql2);
            pst.execute();
            pst.close();
            
            
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    static public Canil getCanilByTitulo(String titulo){
        connect(true);
        Canil output = null;
        try {
            String sql = "SELECT * FROM canil WHERE titulo='"+titulo+"'";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output = new Canil(
                        rs.getInt("idcanil"),
                        rs.getString("titulo"),
                        rs.getString("porte")
                );
            }

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Get erro " +e);
        }
     
        return output;
    }
    
}
