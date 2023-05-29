package gestaopet.DB;

import static gestaopet.DB.PetDB.connect;
import gestaopet.classes.Pessoa;
import gestaopet.classes.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PessoaDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    
    
    
    public PessoaDB(){
        con = DbConnection.ConnectionDB();
        try {
            con.setAutoCommit(true);
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
        
        

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
    
    public static Pessoa insert(Pessoa person){
        
        Pessoa p = null;
        connect(true);
        
        try {
            
            String sql = "INSERT INTO pessoa ("
                    + "tipo, "
                    + "doc, "
                    + "nome1, "
                    + "nome2, "
                    + "email, "
                    + "tel1, "
                    + "tel2, "
                    + "tel3, "
                    + "endereco, "
                    + "cidade, "
                    + "estado, "
                    + "obs)"
                    + " VALUES ("
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?, "
                    + "?);";

            pst = con.prepareStatement(sql);
            
            pst.setString(1, person.getTipo());
            pst.setString(2, person.getDoc());
            pst.setString(3, person.getNome1());
            pst.setString(4, person.getNome2());
            pst.setString(5, person.getEmail());
            pst.setString(6, person.getTel1());
            pst.setString(7, person.getTel2());
            pst.setString(8, person.getTel3());
            pst.setString(9, person.getEndereco());
            pst.setString(10, person.getCidade());
            pst.setString(11, person.getEstado());
            pst.setString(12, person.getObs());
            
            pst.execute();

            
            connect(false);
            
            p = getByDoc(person.getDoc());
            
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        
        return p;
    }
    
    public static void delete(int id){
        connect(true);

        try {
            String sql = "DELETE FROM pessoa WHERE id =" + id;

            pst = con.prepareStatement(sql);

            pst.execute();
            pst.close();
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
    
    
    public static List<Pessoa> getAll(String tipo){
        connect(true);
        List<Pessoa> output = new ArrayList<Pessoa>();
        try {
            String sql = "SELECT * FROM pessoa WHERE tipo = '" + tipo + "';";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                Pessoa p = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("doc"),
                        rs.getString("nome1"),
                        rs.getString("nome2"),
                        rs.getString("email"),
                        rs.getString("tel1"),
                        rs.getString("tel2"),
                        rs.getString("tel3"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("obs")                                
                );
                output.add(p);
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return output;
    }
    
    public static Pessoa getByDoc(String doc){
        connect(true);
        Pessoa p = null;
        try {
            String sql = "SELECT * FROM pessoa WHERE doc = '" + doc + "';";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                
                p = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("doc"),
                        rs.getString("nome1"),
                        rs.getString("nome2"),
                        rs.getString("email"),
                        rs.getString("tel1"),
                        rs.getString("tel2"),
                        rs.getString("tel3"),
                        rs.getString("endereco"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getString("obs")                                
                );
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return p;
    }
    
    /*
            tipo,
            doc,
            nome1,
            nome2,
            email,
            tel1,
            tel2,
            tel3,
            endereco,
            cidade,
            estado,
            obs
    */
    
    public static void update(Pessoa pessoa){
        connect(true);
        String[] values = pessoa.getValues();
        try {
            String sql = "UPDATE pessoa " +
                        "SET " +
                        "tipo = ?, " +
                        "doc = ?, " +
                        "nome1 = ?, " +
                        "nome2 = ?, " +
                        "email = ?, " +
                        "tel1 = ?, " +
                        "tel2 = ?, " +
                        "tel3 = ?, " +
                        "endereco = ?, " +
                        "cidade = ?, " +
                        "estado = ?, " +
                        "obs = ? " +
                        "WHERE id = "+pessoa.getId();

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
    
    
    
    public static boolean isRegistred(String id){
        connect(true);
        try {
            String sql = "SELECT COUNT(*) AS count FROM pessoa WHERE doc ='" + id + "'";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            pst = null;
            int count = rs.getInt("count");
            connect(false);
            return (count == 0)? false: true;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return false;
    }
}
