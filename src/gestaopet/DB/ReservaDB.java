package gestaopet.DB;

import static gestaopet.DB.PetDB.connect;
import gestaopet.classes.DateTools;
import gestaopet.classes.Pet;
import gestaopet.classes.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservaDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    public ReservaDB(){
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
    public static void insertIncludes(List<Pet> pets, String token){
        int id =  getByToken(token).get(0).getId();
        connect(true);
        
        try {
            //INSERT INTO agregadosDaReserva (idPed, token) VALUES (?,?);
            for(int i = 0; i < pets.size(); i++){
                String sql = "INSERT INTO agregadosDaReserva (idPet, idReserva) VALUES (?,?)";
                pst = con.prepareStatement(sql);
                pst.setInt(1, pets.get(i).getId());
                pst.setInt(2, id);
                pst.execute();
                pst = null;
            }

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
    }
//                    id,
//                    nome,
//                    raca,
//                    nascimento,
//                    genero,
//                    porte,
//                    sociabilidade,
//                    castrado,
//                    foto,
//                    acessorio,
//                    obs,
//                    idPessoa
//int id, String nome, String raca, Date nascimento, 
//String genero, String porte, boolean sociavel, 
//boolean castrado, String foto, String acessorio, 
//String obs, String donoNome, int donoId
    public static List<Pet> getIncludes(int idReserva){
        connect(true);
        List<Pet> output = new ArrayList<>();
        try {

            String sql = "SELECT * FROM pet as P LEFT JOIN agregadosDaReserva as A ON A.idPet = P.id WHERE A.idReserva =" + idReserva;

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Pet(
//                        rs.getInt("id"),
//                        rs.getString("nome"),
//                        rs.getString("raca"),
//                        DateTools.SQLToDate(rs.getString("nascimento"), ""),
//                        rs.getString("genero"),
//                        rs.getString("porte"),
//                        rs.getBoolean("sociabilidade"),
//                        rs.getBoolean("castrado"),
//                        rs.getString("foto"),
//                        rs.getString("acessorio"),
//                        rs.getString("obs"),

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
//                        rs.getString("dono"),
//                        rs.getInt("donoid")
                        "",
                        rs.getInt("idPessoa")

                ));
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }

        return output;
    }
    
    public static void insert(Reserva reserva){
        connect(true);
        String[] values = reserva.getValues();
        try {
            String sql = "INSERT INTO reserva (" +
                            "idalojamento, " +
                            "idpet, " +
                            "status, " +
                            "checkin, " +
                            "checkout, " +
                            "valordiaria, " +
                            "tipo, " +
                            "token, " +
                            "diastotais " +
                            ") " +
                            "VALUES (" +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?, " +
                            "? " +
                            ");";

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

//    SELECT R.idreserva, R.idalojamento, R.idpet, R.status, R.checkin, R.checkout, R.valordiaria, C.titulo
//    FROM reserva as R
//    LEFT JOIN alojamento as A
//    ON R.idalojamento = A.idalojamento
//    LEFT JOIN canil as C
//    ON A.idcanil = C.idcanil
//    WHERE (R.checkin <= '" +  + "' AND R.checkout >= '" +  + "') AND R.idpet =" + petid
    
    
    public static List<Reserva> getAll(Date i, Date f, int petid, boolean checkin){
        connect(true);
        List<Reserva> output = new ArrayList<>();
        try {
            String di = DateTools.dateToSQL(i);
            String df = DateTools.dateToSQL(f);
            String check = (checkin)? "R.checkin": "R.checkout";
            String sql =      "SELECT R.idreserva, R.idalojamento, R.idpet, R.status, R.checkin, R.checkout, R.valordiaria, R.tipo, R.token, R.diastotais, R.situacao, C.titulo, A.titulo as tituloalojamento "
                            + "FROM reserva as R "
                            + "LEFT JOIN alojamento as A "
                            + "ON R.idalojamento = A.idalojamento "
                            + "LEFT JOIN canil as C "
                            + "ON A.idcanil = C.idcanil"
                            + " WHERE ("+ check +" <= '" + df + "' AND "+ check +" >= '" + di + "')" + ((petid >= 0)? " AND R.idpet =" + petid : "") + " ORDER by " + check + " ASC";
                            //" WHERE (R.checkin <= '" + df + "' AND R.checkout >= '" + di + "') AND A.idcanil = " + canilId;

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Reserva(
                        rs.getInt("idreserva"),
                        rs.getInt("idalojamento"),
                        rs.getString("tituloalojamento"),
                        rs.getInt("idpet"),
                        rs.getString("status"),
                        DateTools.SQLToDate(rs.getString("checkin"),"00:00"),
                        DateTools.SQLToDate(rs.getString("checkout"),"00:00"),
                        rs.getDouble("valordiaria"),
                        rs.getInt("tipo"),
                        rs.getString("token"),
                        rs.getInt("diastotais"),
                        rs.getBoolean("situacao"),
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
    
    
    public static List<Reserva> getAllById(Date i, Date f, int petid, boolean checkin){
        connect(true);
        List<Reserva> output = new ArrayList<>();
        try {
            String di = DateTools.dateToSQL(i);
            String df = DateTools.dateToSQL(f);
            String check = (checkin)? "R.checkin": "R.checkout";
            String sql = "SELECT R.idreserva, R.idalojamento, R.idpet, R.status, R.checkin, R.checkout, R.valordiaria, R.tipo, R.token, R.diastotais, R.situacao, C.titulo, A.titulo as tituloalojamento "
                            + "FROM reserva as R "
                            + "LEFT JOIN alojamento as A "
                            + "ON R.idalojamento = A.idalojamento "
                            + "LEFT JOIN canil as C "
                            + "ON A.idcanil = C.idcanil "
							+ "LEFT JOIN agregadosDaReserva as ADR "
							+ "ON ADR.idReserva = R.idreserva "
                            + " WHERE ("+ check +" <= '" + df + "' AND "+ check +" >= '" + di + "')" + ((petid >= 0)? " AND ADR.idpet =" + petid : "") + " ORDER by " + check + " ASC";

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Reserva(
                        rs.getInt("idreserva"),
                        rs.getInt("idalojamento"),
                        rs.getString("tituloalojamento"),
                        rs.getInt("idpet"),
                        rs.getString("status"),
                        DateTools.SQLToDate(rs.getString("checkin"),"00:00"),
                        DateTools.SQLToDate(rs.getString("checkout"),"00:00"),
                        rs.getDouble("valordiaria"),
                        rs.getInt("tipo"),
                        rs.getString("token"),
                        rs.getInt("diastotais"),
                        rs.getBoolean("situacao"),
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
    
    
    public static List<Reserva> getByDate(Date i, Date f, int canilId){
        connect(true);
        List<Reserva> output = new ArrayList<>();
        try {
            String di = DateTools.dateToSQL(i);
            String df = DateTools.dateToSQL(f);
            String sql = "SELECT R.idreserva, R.idalojamento, R.idpet, R.status, R.checkin, R.checkout, R.valordiaria, R.tipo, R.token, R.diastotais, R.situacao, A.titulo as tituloalojamento " +
                            "FROM reserva as R " +
                            "LEFT JOIN alojamento as A " +
                            "ON R.idalojamento = A.idalojamento " +
                            "WHERE (R.checkin <= '" + df + "' AND R.checkout >= '" + di + "') AND A.idcanil = " + canilId;

            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Reserva(
                        rs.getInt("idreserva"),
                        rs.getInt("idalojamento"),
                        rs.getString("tituloalojamento"),
                        rs.getInt("idpet"),
                        rs.getString("status"),
                        DateTools.SQLToDate(rs.getString("checkin"),"00:00"),
                        DateTools.SQLToDate(rs.getString("checkout"),"00:00"),
                        rs.getDouble("valordiaria"),
                        rs.getInt("tipo"),
                        rs.getString("token"),
                        rs.getInt("diastotais"),
                        rs.getBoolean("situacao")
                ));
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        

        
        
        return output;
    }
    
    public static List<Reserva> getByToken(String token){
        connect(true);
        List<Reserva> output = new ArrayList<>();
        try {
            String sql =  "SELECT R.idreserva, R.idalojamento, R.idpet, R.status, R.checkin, R.checkout, R.valordiaria, R.tipo, R.token, R.diastotais, R.situacao, C.titulo, A.titulo as tituloalojamento "
                        + "FROM reserva as R "
                        + "LEFT JOIN alojamento as A "
                        + "ON R.idalojamento = A.idalojamento "
                        + "LEFT JOIN canil as C "
                        + "ON A.idcanil = C.idcanil"
                        + " WHERE R.token ='" + token + "' ORDER by R.idreserva ASC";


            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                output.add( new Reserva(
                        rs.getInt("idreserva"),
                        rs.getInt("idalojamento"),
                        rs.getString("tituloalojamento"),
                        rs.getInt("idpet"),
                        rs.getString("status"),
                        DateTools.SQLToDate(rs.getString("checkin"),"00:00"),
                        DateTools.SQLToDate(rs.getString("checkout"),"00:00"),
                        rs.getDouble("valordiaria"),
                        rs.getInt("tipo"),
                        rs.getString("token"),
                        rs.getInt("diastotais"),
                        rs.getBoolean("situacao"),
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
    
    
    public static void update(Reserva reserva){
        connect(true);
        String[] values = {reserva.getPet() + "",reserva.getStatus(), reserva.getValor() + "", (reserva.isSituacao())? "1" : "0"};
        try {      
            String sql = "UPDATE reserva " +
                        "SET " +
                        "idpet = ?, " +
                        "status = ?, " +
                        "valordiaria = ?, " +
                        "situacao = ? " +
                        "WHERE token = '"+reserva.getToken()+"'";

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
    
}
