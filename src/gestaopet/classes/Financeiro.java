
package gestaopet.classes;

import java.util.Date;

public class Financeiro {
    private int idFinanceiro;
    private String tipoOperacao;
    private String categoria;
    private String descricao;
    private Double valor;
    private Double taxa;
    private int idItemOrigem;
    private String idTabelaOrigem;
    private Date dataLancamento;
    private int statusPagamento;
    private int ishide;

    public Financeiro(int idFinanceiro, String tipoOperacao, String categoria, String descricao, Double valor, Double taxa, int idItemOrigem, String idTabelaOrigem, Date dataLancamento, int statusPagamento, int ishide) {
        this.idFinanceiro = idFinanceiro;
        this.tipoOperacao = tipoOperacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.taxa = taxa;
        this.idItemOrigem = idItemOrigem;
        this.idTabelaOrigem = idTabelaOrigem;
        this.dataLancamento = dataLancamento;
        this.statusPagamento = statusPagamento;
        this.ishide = ishide;
    }

    public Financeiro(String tipoOperacao, String categoria, String descricao, Double valor, Double taxa, int idItemOrigem, String idTabelaOrigem, Date dataLancamento, int statusPagamento, int ishide) {
        this.tipoOperacao = tipoOperacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.taxa = taxa;
        this.idItemOrigem = idItemOrigem;
        this.idTabelaOrigem = idTabelaOrigem;
        this.dataLancamento = dataLancamento;
        this.statusPagamento = statusPagamento;
        this.ishide = ishide;
    }

    public Financeiro(String tipoOperacao, String categoria, String descricao, Double valor, Double taxa, Date dataLancamento, int statusPagamento, int ishide) {
        this.tipoOperacao = tipoOperacao;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
        this.taxa = taxa;
        this.dataLancamento = dataLancamento;
        this.statusPagamento = statusPagamento;
        this.ishide = ishide;
    }
    
    

    public int getIdFinanceiro() {
        return idFinanceiro;
    }

    public void setIdFinanceiro(int idFinanceiro) {
        this.idFinanceiro = idFinanceiro;
    }

    public String getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(String tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getTaxa() {
        return taxa;
    }

    public void setTaxa(Double taxa) {
        this.taxa = taxa;
    }

    public int getIdItemOrigem() {
        return idItemOrigem;
    }

    public void setIdItemOrigem(int idItemOrigem) {
        this.idItemOrigem = idItemOrigem;
    }

    public String getIdTabelaOrigem() {
        return idTabelaOrigem;
    }

    public void setIdTabelaOrigem(String idTabelaOrigem) {
        this.idTabelaOrigem = idTabelaOrigem;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
        
        
    }

    public int getStatusPagamento() {
        return statusPagamento;
    }

    public void setStatusPagamento(int statusPagamento) {
        this.statusPagamento = statusPagamento;
    }

    public int getIshide() {
        return ishide;
    }

    public void setIshide(int ishide) {
        this.ishide = ishide;
    }
    
    
    
    
}
