
package gestaopet.classes;

import java.util.Date;

public class FinanceiroItem {
    private int idOrgigem;
    private String tabelaOrigem;
    private String descricao;
    private Double valor;
    private String pessoa;
    private Date data;
    private int quantidade;
    private int situacao;

    public FinanceiroItem(int idOrgigem, String tabelaOrigem, String descricao, Double valor, String pessoa, Date data, int quantidade, int situacao) {
        this.idOrgigem = idOrgigem;
        this.tabelaOrigem = tabelaOrigem;
        this.valor = valor;
        this.pessoa = pessoa;
        this.data = data;
        this.quantidade = quantidade;
        this.situacao = situacao;
        this.descricao = descricao;
    }

    public int getIdOrgigem() {
        return idOrgigem;
    }

    public void setIdOrgigem(int idOrgigem) {
        this.idOrgigem = idOrgigem;
    }

    public String getTabelaOrigem() {
        return tabelaOrigem;
    }

    public void setTabelaOrigem(String tabelaOrigem) {
        this.tabelaOrigem = tabelaOrigem;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getPessoa() {
        return pessoa;
    }

    public void setPessoa(String pessoa) {
        this.pessoa = pessoa;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
    
}
