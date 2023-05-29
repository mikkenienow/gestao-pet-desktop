package gestaopet.classes;

import java.util.Date;

public class Estoque {
    private int idEstoque;
    private String produto;
    private double valor;
    private int quantidade;
    private String faturavel;
    private String categoria;
    private Date dataEntrada;
    private Date dataVencimento;
    private String formaPagamento;
    private int situacao;
    private int idFornecedor;

    public Estoque(int idEstoque, String produto, double valor, int quantidade, String faturavel, String categoria, Date dataEntrada, Date dataVencimento, String formaPagamento, int situacao, int idFornecedor) {
        this.idEstoque = idEstoque;
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.faturavel = faturavel;
        this.categoria = categoria;
        this.dataEntrada = dataEntrada;
        this.dataVencimento = dataVencimento;
        this.formaPagamento = formaPagamento;
        this.situacao = situacao;
        this.idFornecedor = idFornecedor;
    }

    public Estoque(String produto, double valor, int quantidade, String faturavel, String categoria, Date dataEntrada, Date dataVencimento, String formaPagamento, int situacao, int idFornecedor) {
        this.produto = produto;
        this.valor = valor;
        this.quantidade = quantidade;
        this.faturavel = faturavel;
        this.categoria = categoria;
        this.dataEntrada = dataEntrada;
        this.dataVencimento = dataVencimento;
        this.formaPagamento = formaPagamento;
        this.situacao = situacao;
        this.idFornecedor = idFornecedor;
    }
    
    

    public int getIdEstoque() {
        return idEstoque;
    }

    public String getProduto() {
        return produto;
    }

    public double getValor() {
        return valor;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public String getFaturavel() {
        return faturavel;
    }

    public String getCategoria() {
        return categoria;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void setFaturavel(String faturavel) {
        this.faturavel = faturavel;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }
    
    
    
    
    
    public String[] getValues(){
        String[] output = {
        idEstoque + "",
        produto,
        valor + "",
        quantidade + "",
        faturavel + "",
        categoria,
        dataEntrada + "",
        dataVencimento + "",
        formaPagamento,
        situacao + "",
        idFornecedor + ""
        };
        return output;
    }
    
    
    
}
