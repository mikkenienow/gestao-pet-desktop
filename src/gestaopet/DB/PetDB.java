package gestaopet.DB;


import gestaopet.classes.DateTools;
import gestaopet.classes.Pet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PetDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    
    
    
    public PetDB(){
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
    
    public static void insert(Pet pet){
        connect(true);
        String[] values = pet.getValues();
        try {
            String sql = "INSERT INTO pet ("
                    + "nome, "
                    + "raca, "
                    + "nascimento, "
                    + "genero, "
                    + "porte, "
                    + "sociabilidade, "
                    + "castrado, "
                    + "foto, "
                    + "acessorio, "
                    + "obs,"
                    + "idpessoa)"
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
    
    public static void update(Pet pet){
        connect(true);
        String[] values = pet.getValues();
        try {
            String sql = "UPDATE pet " +
                        "SET " +
                        "nome = ?, " +
                        "raca = ?, " +
                        "nascimento = ?, " +
                        "genero = ?, " +
                        "porte = ?, " +
                        "sociabilidade = ?, " +
                        "castrado = ?, " +
                        "foto = ?, " +
                        "acessorio = ?, " +
                        "obs = ?, " +
                        "idPessoa = ? " +
                        "WHERE id = "+pet.getId();

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
            String sql = "DELETE FROM pet WHERE id =" + id;

            pst = con.prepareStatement(sql);
            /*
            for(int i = 0; i < values.length; i++){
                pst.setString(i+1, values[i]);
            }*/
            pst.execute();
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }

    
    public static List<Pet> getAll(){
        connect(true);
        List<Pet> output = new ArrayList<Pet>();
        try {
            String sql =    "SELECT A.id, A.nome, A.raca, A.nascimento, A.genero, A.porte, " +
                            "A.sociabilidade, A.castrado, A.foto, A.acessorio," +
                            "A.obs, B.id AS donoid, B.nome1 || ' ' || IFNULL(B.nome2, '') AS dono " +
                            "FROM pet AS A " +
                            "LEFT JOIN pessoa AS B " +
                            "ON A.idPessoa = B.id ORDER by A.nome ASC ";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Pet(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("raca"),
                        DateTools.SQLToDate(rs.getString("nascimento"), "00:00"),
                        rs.getString("genero"),
                        rs.getString("porte"),
                        rs.getBoolean("sociabilidade"),
                        rs.getBoolean("castrado"),
                        rs.getString("foto"),
                        rs.getString("acessorio"),
                        rs.getString("obs"),
                        rs.getString("dono"),
                        rs.getInt("donoid")
                ));
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return output;
    }
    
    public static List<Pet> getByOwner(int ownerId){
        connect(true);
        List<Pet> output = new ArrayList<Pet>();
        try {
            String sql =    "SELECT A.id, A.nome, A.raca, A.nascimento, A.genero, A.porte, " +
                            "A.sociabilidade, A.castrado, A.foto, A.acessorio," +
                            "A.obs, B.id AS donoid, B.nome1 || ' ' || IFNULL(B.nome2, '') AS dono " +
                            "FROM pet AS A " +
                            "LEFT JOIN pessoa AS B " +
                            "ON A.idPessoa = B.id WHERE A.idpessoa=" + ownerId + " ORDER by A.nome ASC ";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Pet(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("raca"),
                        DateTools.SQLToDate(rs.getString("nascimento"), "00:00"),
                        rs.getString("genero"),
                        rs.getString("porte"),
                        rs.getBoolean("sociabilidade"),
                        rs.getBoolean("castrado"),
                        rs.getString("foto"),
                        rs.getString("acessorio"),
                        rs.getString("obs"),
                        rs.getString("dono"),
                        rs.getInt("donoid")
                ));
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return output;
    }
    
    public static Pet getById(int id){
        connect(true);
        Pet output = null;
        try {
            String sql =    "SELECT A.id, A.nome, A.raca, A.nascimento, A.genero, A.porte, " +
                            "A.sociabilidade, A.castrado, A.foto, A.acessorio," +
                            "A.obs, B.id AS donoid, B.nome1 || ' ' || IFNULL(B.nome2, '') AS dono " +
                            "FROM pet AS A " +
                            "LEFT JOIN pessoa AS B " +
                            "ON A.idPessoa = B.id WHERE A.id = " + id + " ORDER by A.nome ASC ";
            
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output = new Pet(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("raca"),
                        DateTools.SQLToDate(rs.getString("nascimento"), "00:00"),
                        rs.getString("genero"),
                        rs.getString("porte"),
                        rs.getBoolean("sociabilidade"),
                        rs.getBoolean("castrado"),
                        rs.getString("foto"),
                        rs.getString("acessorio"),
                        rs.getString("obs"),
                        rs.getString("dono"),
                        rs.getInt("donoid")
                );
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return output;
    }

    public static boolean isRegistred(String nome, int donoId){
        connect(true);
        try {
            String sql = "SELECT COUNT(*) AS count FROM pet WHERE nome LIKE '%" + nome + "%' AND idPessoa=" + donoId;
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
/* int id, String nome, String raca, Date nascimento, String genero, String porte, boolean sociavel, boolean castrado, String foto, String acessorio, String obs, int dono */