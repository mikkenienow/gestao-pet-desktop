package gestaopet.DB;

import gestaopet.DB.DbConnection;
import gestaopet.classes.DateTools;
import gestaopet.classes.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EstoqueDB {

    private static Connection con = null;
    private static PreparedStatement pst = null;
    private static ResultSet rs = null;

    public EstoqueDB() {
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

    public static List<Estoque> getAll(int qntd, String qntdParam, int value, String valueParam, String category, String product) {
        connect(true);
        List<Estoque> data = new ArrayList<>();

        int y = 0;
        String sqlBase = "SELECT * FROM estoque WHERE ";
        String param1 = qntdParam.equals("Maior que") ? ">=" : "<=";
        String param2 = valueParam.equals("Maior que") ? ">=" : "<=";

        if (qntd > 0) {
            sqlBase += " quantidade " + param1 + " " + qntd + " AND";
            y += 1;
        }

        if (value > 0) {
            sqlBase += " valor " + param2 + " " + value + " AND";
            y += 1;
        }

        if (category.length() > 0) {
            sqlBase += " categoria LIKE '%" + category + "%' AND";
            y += 1;
        }

        if (product.length() > 0) {
            sqlBase += " produto LIKE '%" + product + "%' AND";
            y += 1;
        }

        try {
            if (y > 0) {
                sqlBase = sqlBase.substring(0, (sqlBase.length()) - 3);
            } else {
                sqlBase = sqlBase.substring(0, (sqlBase.length()) - 6);
            }

            pst = con.prepareStatement(sqlBase);
            rs = pst.executeQuery();
            while (rs.next()) {
                Estoque estoque = new Estoque(
                        rs.getInt("idProduto"),
                        rs.getString("produto"),
                        rs.getDouble("valor"),
                        rs.getInt("quantidade"),
                        rs.getString("faturavel"),
                        rs.getString("categoria"),
                        DateTools.SQLToDate(rs.getString("dataEntrada"), "00:00"),
                        DateTools.SQLToDate(rs.getString("dataVencimento"), "00:00"),
                        rs.getString("formaPagamento"),
                        rs.getInt("situacao"),
                        rs.getInt("idFornecedor")
                );
                data.add(estoque);
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("erro " + e);
        }
        return data;
    }

    public static int insert(Estoque estoque) {
        int estoqueReturn = 9999999;
        connect(true);

        try {
            String sql = "INSERT INTO estoque ("
                    + "produto, "
                    + "valor, "
                    + "quantidade, "
                    + "faturavel, "
                    + "categoria, "
                    + "dataEntrada, "
                    + "dataVencimento, "
                    + "formaPagamento, "
                    + "situacao, "
                    + "idFornecedor) "
                    + " VALUES ("
                    + "'" + estoque.getProduto() + "', "
                    + estoque.getValor() + ", "
                    + estoque.getQuantidade() + ", "
                    + "'" + estoque.getFaturavel() + "', "
                    + "'" + estoque.getCategoria() + "', "
                    + "'" + DateTools.dateToSQL(estoque.getDataEntrada()) + "', "
                    + "'" + DateTools.dateToSQL(estoque.getDataVencimento()) + "', "
                    + "'" + estoque.getFormaPagamento() + "', "
                    + estoque.getSituacao() + ", " 
                    + estoque.getIdFornecedor() + "); ";

            pst = con.prepareStatement(sql);
            pst.execute();
            String sql2 = "SELECT idProduto FROM estoque ORDER BY idProduto DESC LIMIT 1";
            pst = con.prepareStatement(sql2);
            rs = pst.executeQuery();
            while(rs.next()){
                estoqueReturn = rs.getInt("idProduto");
            }
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return estoqueReturn;
    }
    
    public static String delete(int id) {
        String estoqueReturn = null;
        connect(true);

        try {
            String sql = "DELETE FROM estoque WHERE idProduto = " + id + ";";

            pst = con.prepareStatement(sql);
            pst.execute();
            estoqueReturn = "ok";
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return estoqueReturn;
    }
    
        public static String update(Estoque estoque, int id) {
        String estoqueReturn = null;
        connect(true);
        
        try {
            String sql = "UPDATE estoque SET " +
                    "produto = '" + estoque.getProduto() + "', " +
                    "valor = " + estoque.getValor() + ", " +
                    "quantidade = " + estoque.getQuantidade()+ ", " +
                    "faturavel = '" + estoque.getProduto() + "', " +
                    "categoria = '" + estoque.getCategoria() + "', " +
                    "dataEntrada = '" + DateTools.dateToSQL(estoque.getDataEntrada()) + "', " +
                    "dataVencimento = '" + DateTools.stringDateToSQL(estoque.getDataVencimento() + "") + "', " +
                    "formaPagamento = '" + estoque.getFormaPagamento() + "', " +
                    "situacao = " + estoque.getSituacao() + ", " +
                    "idFornecedor = " + estoque.getIdFornecedor() + 
                    " WHERE idProduto = " + id + " ;";
            pst = con.prepareStatement(sql);
            pst.execute();
            estoqueReturn = "ok";
            connect(false);
            pst = null;
        } catch (Exception e) {
            System.out.println("Erro: " + e);
        }
        return estoqueReturn;
    }

}
