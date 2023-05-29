package gestaopet.DB;
import static gestaopet.DB.PessoaDB.connect;
import static gestaopet.DB.PetDB.connect;
import gestaopet.classes.DateTools;
import gestaopet.classes.Pessoa;
import gestaopet.classes.Servico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
public class ServicoDB {
    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;
    public ServicoDB(){
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
    public static void update(Servico servico){
        connect(true);
        String[] values = servico.getValues();
        try {
            String sql = "UPDATE servico " +
                        "SET " +
                        "servico = ?, " +
                        "data = ?, " +
                        "hora = ?, " +
                        "idPet = ?, " +
                        "valor = ?, " +
                        "lembrete = ?, " +
                        "situacao = ? " +
                        "WHERE idServico = "+servico.getId();
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
    public static void insert(Servico servico){
        Pessoa p = null;
        connect(true);
        String[] values = servico.getValues();
        try {
            String sql = "INSERT INTO servico ("
                    + "servico, "
                    + "data, "
                    + "hora, "
                    + "idPet, "
                    + "valor, "
                    + "lembrete, "
                    + "situacao)"
                    + " VALUES ("
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
    public static List<Servico> getByPet(int petId, String serviceType, String period, String today){
        connect(true);
        List<Servico> serviceList = new ArrayList<>();
        String sqlBase = "SELECT * FROM servico WHERE";
        String sqlFinal = "";
        int concatenationIndex = 0;
        if(petId >= 0){
            sqlBase += " idPet = " + petId + " AND";
            concatenationIndex += 1;
        }
        System.out.println(period);
        switch (period) {
            case "hoje":
                sqlBase += " data = '" + today + "' AND";
                concatenationIndex += 1;
                break;
            case "pendente":
                sqlBase += " data >= '" + today + "' AND";
                concatenationIndex += 1;
                break;
            default:
                break;
        }
        if(serviceType == "creche" || serviceType == "banho" || serviceType == "banhoetosa"){
            sqlBase += " servico = '" + serviceType + "' AND";
            concatenationIndex += 1;
        }
        if(concatenationIndex > 0){
            sqlFinal = sqlBase.substring(0, sqlBase.length() - 3);
        }else{
            sqlFinal = sqlBase.substring(0, sqlBase.length() - 5);
        }
        System.out.println(sqlFinal);
        try {
            pst = con.prepareStatement(sqlFinal);
            rs = pst.executeQuery();
            int i = 0;
            while(rs.next()){
                serviceList.add(new Servico(
                        rs.getInt("idServico"),
                        rs.getString("servico"),
                        DateTools.SQLToDate(rs.getString("data"), rs.getString("hora")),
                        rs.getInt("idPet"),
                        rs.getDouble("valor"),
                        rs.getBoolean("lembrete"),
                        rs.getBoolean("situacao")
                ));
                i++;
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return serviceList;
    }
    public static List<Servico> getAll(){
        connect(true);
        List<Servico> s = new ArrayList<>();
        try {
            String sql = "SELECT * FROM servico ORDER BY data ASC";
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while(rs.next()){
                s.add(new Servico(
                        rs.getInt("idServico"),
                        rs.getString("servico"),
                        DateTools.SQLToDate(rs.getString("data"), rs.getString("hora")),
                        rs.getInt("idPet"),
                        rs.getDouble("valor"),
                        rs.getBoolean("lembrete"),
                        rs.getBoolean("situacao")
                ));
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " +e);
        }
        return s;
    }
    public static void delete(int id){
        connect(true);
        try {
            String sql = "DELETE FROM servico WHERE idServico =" + id;
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
}