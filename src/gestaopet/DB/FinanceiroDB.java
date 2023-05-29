package gestaopet.DB;

import gestaopet.DB.DbConnection;
import gestaopet.classes.DateTools;
import gestaopet.classes.Financeiro;
import gestaopet.classes.FinanceiroItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FinanceiroDB {

    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    public FinanceiroDB() {
        con = DbConnection.ConnectionDB();

    }

    public static void connect(boolean connect) {
        if (connect) {
            con = DbConnection.ConnectionDB();
        } else {
            try {
                if (!con.isClosed()) {
                    con.close();
                }
            } catch (Exception e) {
                System.out.println("Erro ao fechar conexão: " + e);
            }
        }
    }

public static List<Financeiro> getAll(String status, String initialDate, String endDate, boolean hide) {
        connect(true);
        List<Financeiro> data = new ArrayList<>();
        String sqlBase = "SELECT * FROM financeiro";
        String sqlDate = null;
        String initialFormatted = null;
        String endFormatted = null;
        Boolean dateFilter = false;
        
        
        
        try {
            initialFormatted = DateTools.stringDateToSQL(initialDate);
        } catch (Exception e) {
        }
        
        try {
            endFormatted = DateTools.stringDateToSQL(endDate);
        } catch (Exception e) {
        }

        if(initialFormatted != null && endFormatted != null){
            sqlDate = " dataLancamento > '" + initialFormatted + "' AND dataLancamento < '" + endFormatted + "'";
            dateFilter = true;
        }else if(initialFormatted != null){
            sqlDate = " dataLancamento > '" + initialFormatted + "'";
            dateFilter = true;
        }else if(endFormatted != null){
            sqlDate = " dataLancamento < '" + endFormatted + "'";
            dateFilter = true;
        }
        
        
        if (status == "Pagos") {
            sqlBase += " WHERE statusPagamento = 1";
            if(dateFilter == true){
                sqlBase += " AND " + sqlDate;
            }

        } else if (status == "Pendentes") {
            sqlBase += " WHERE statusPagamento = 0";
            if(dateFilter == true){
                sqlBase += " AND " + sqlDate;
            }
            
        }else{
            sqlBase += "";
            if(dateFilter == true){
                sqlBase += " WHERE " + sqlDate;
            }
        }

        sqlBase += "AND oculto="+ ((hide)? 1:0) +" ORDER BY dataLancamento ASC";

        try {
            pst = con.prepareStatement(sqlBase);
            rs = pst.executeQuery();
            while (rs.next()) {
                Financeiro financeiro = new Financeiro(
                        rs.getInt("idFinanceiro"),
                        rs.getString("tipoOperacao"),
                        rs.getString("categoria"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getDouble("taxa"),
                        rs.getInt("idItem"),
                        rs.getString("tabelaOrigem"),
                        DateTools.SQLToDate(rs.getString("dataLancamento"), "00:00"),
                        rs.getInt("statusPagamento"),
                        rs.getInt("oculto")
                );
                data.add(financeiro);
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
        return data;
    }

    public static List<FinanceiroItem> getPendency() {
        connect(true);
        List<FinanceiroItem> data = new ArrayList<>();
        int y = 0;

        try {
            String sqlQuery = "SELECT * FROM estoque WHERE situacao = 0";
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            while (rs.next()) {
                FinanceiroItem financeiro = new FinanceiroItem(
                        rs.getInt("idProduto"),
                        "Estoque",
                        rs.getString("produto"),
                        rs.getDouble("valor"),
                        "Próprio",
                        DateTools.SQLToDate(rs.getString("dataEntrada"), "00:00"),
                        rs.getInt("quantidade"),
                        rs.getInt("situacao")
                );
                data.add(financeiro);
            }

            sqlQuery = "SELECT S.idServico, S.servico, S.data,S.hora,"
                    + "S.idPet, S.valor,S.lembrete,S.situacao,PE.nome1, PE.nome2"
                    + "  FROM servico AS S LEFT JOIN pet as P "
                    + "  ON S.idPet = P.id"
                    + "  LEFT JOIN pessoa as PE"
                    + "  ON P.idPessoa = PE.id"
                    + "  WHERE S.situacao = 0;";

            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            while (rs.next()) {
                FinanceiroItem financeiro = new FinanceiroItem(
                        rs.getInt("idServico"),
                        "Serviço",
                        rs.getString("servico"),
                        rs.getDouble("valor"),
                        rs.getString("nome1") + " " + rs.getString("nome2"),
                        DateTools.SQLToDate(rs.getString("data"), "00:00"),
                        1,
                        rs.getInt("situacao")
                );
                data.add(financeiro);
            }

            sqlQuery = "SELECT R.idreserva, (R.valorDiaria * R.diasTotais) AS valor, R.checkin, R.diastotais, R.situacao, PE.nome1, PE.nome2"
                    + "  FROM reserva AS R LEFT JOIN pet as P "
                    + "  ON R.idPet = P.id"
                    + "  LEFT JOIN pessoa as PE"
                    + "  ON P.idPessoa = PE.id"
                    + "  WHERE R.situacao = 0 AND R.tipo <= 1;";
            pst = con.prepareStatement(sqlQuery);
            rs = pst.executeQuery();
            while (rs.next()) {
                FinanceiroItem financeiro = new FinanceiroItem(
                        rs.getInt("idReserva"),
                        "Hospedagem",
                        "Reserva de " + rs.getString("diastotais") + " dias",
                        rs.getDouble("valor"),
                        rs.getString("nome1") + " " + rs.getString("nome2"),
                        DateTools.SQLToDate(rs.getString("checkin"), "00:00"),
                        1,
                        rs.getInt("situacao")
                );
                data.add(financeiro);
            }

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
        return data;
    }

    public static String addFInanceiroItem(FinanceiroItem financeiroItem) {
        connect(true);
        String financeiroReturn = "erro";
        String idColumn = "";
        String tableName = "";
        String tipoOperacao = "Saida";
        int idItem = financeiroItem.getIdOrgigem();

        if (financeiroItem.getTabelaOrigem() == "Estoque") {
            idColumn = "idProduto";
            tableName = "estoque";
        } else if (financeiroItem.getTabelaOrigem() == "Serviço") {
            idColumn = "idServico";
            tableName = "servico";
            tipoOperacao = "Entrada";
        } else if (financeiroItem.getTabelaOrigem() == "Hospedagem") {
            idColumn = "idReserva";
            tableName = "reserva";
            tipoOperacao = "Entrada";
        }

        try {
            if(!financeiroItem.getTabelaOrigem().equals("Avulso")){
                String sqlOrigin = "UPDATE " + tableName
                    + " SET situacao = 1 WHERE " + idColumn + " = " + idItem;
                pst = con.prepareStatement(sqlOrigin);
                pst.execute();
                pst = null;
            
            }
                
            

            String sqlBase = "INSERT INTO financeiro ("
                    + " tipoOperacao, categoria, descricao, "
                    + "valor, taxa, idItem, tabelaOrigem, dataLancamento, statusPagamento) "
                    + "VALUES ("
                    + "'"+ tipoOperacao +"', "
                    + "'" + financeiroItem.getTabelaOrigem() + "', "
                    + "'" + financeiroItem.getDescricao() + "', "
                    + financeiroItem.getValor() + ", "
                    + 0 + ", "
                    + financeiroItem.getIdOrgigem() + ", "
                    + "'" + financeiroItem.getTabelaOrigem() + "', "
                    + "'" + DateTools.dateToSQL(financeiroItem.getData()) + "', "+ financeiroItem.getSituacao() + "); ";
            System.out.println(sqlBase);
            pst = con.prepareStatement(sqlBase);
            pst.execute();
            financeiroReturn = "Inserido com sucesso!";
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
        return financeiroReturn;
    }
    
    public static String setHide(Financeiro financeiro, boolean hide){
        connect(true);
        String response = "erro";
        String sql = "UPDATE financeiro SET oculto ="+ ((hide)? 1:0) +" WHERE idFinanceiro = " + financeiro.getIdFinanceiro();
        
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst = null;
            connect(false);
            response = "Atualizado com Suceso!";

        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
        return response;
    }

    public static String setPayed(Financeiro financeiro, boolean pgto) {
        connect(true);
        String response = "erro";
        String sql = "UPDATE financeiro SET statusPagamento = " + ((pgto)? 1: 0)  + " WHERE idFinanceiro = " + financeiro.getIdFinanceiro();
        try {
            pst = con.prepareStatement(sql);
            pst.execute();
            pst = null;
            connect(false);
            response = "Atualizado com Suceso!";

        } catch (Exception e) {
            System.out.println("erro: " + e);
        }
        return response;
    }
    
    public static String editRate(int id, String rate, String type) {
        connect(true);
        String response = null;
        String signal = (type == "Desconto") ? "-" : "+";
        String sql = "UPDATE financeiro SET taxa = " + signal + rate
                + " WHERE idFinanceiro = " + id;
        if (rate != null) {
            try {
                pst = con.prepareStatement(sql);
                pst.execute();
                pst = null;
                connect(false);
                response = "Atualizado com Suceso!";
            } catch (Exception e) {
                System.out.println("erro" + e);
            }
        }
        return response;
    }

    public static int loadInfo1() {
        connect(true);
        List<Financeiro> data = new ArrayList<>();
        int y = 0;
        int count = 0;
        String[] tabelas = {"estoque WHERE situacao = 0", "servico WHERE situacao = 0", "reserva WHERE situacao = 0 AND tipo <= 1"};

        try {
            for (int i = 0; i < tabelas.length; i++) {
                
                String sqlBase = "SELECT COUNT(*) as count FROM " + tabelas[i];
                pst = con.prepareStatement(sqlBase);
                rs = pst.executeQuery();
                count += rs.getInt("count");
            }

            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
        return count;
    }
}
