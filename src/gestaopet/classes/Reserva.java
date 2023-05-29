package gestaopet.classes;

import java.util.Date;

public class Reserva {
    private int id;
    private int idAlojamento;
    private String alojamentoTitle;
    private int pet;
    private String status;
    private Date checkin;
    private Date checkout;
    private double valor;
    private int tipo;
    private String token;
    private int diasTotais;
    private String canil;
    private boolean situacao;
    
    public Reserva(){
        
    }

    public Reserva(int id, int alojamento, String alojamentoTitle, int pet, String status, Date checkin, Date checkout, Double valor, int tipo, String token, int diasTotais, boolean situacao, String canil) {
        this.id = id;
        this.idAlojamento = alojamento;
        this.alojamentoTitle = alojamentoTitle;
        this.pet = pet;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.valor = valor;
        this.tipo = tipo;
        this.token = token;
        this.diasTotais = diasTotais;
        this.canil = canil;
        this.situacao = situacao;
    }
    
    public Reserva(int id, int alojamento, String alojamentoTitle, int pet, String status, Date checkin, Date checkout, Double valor, int tipo, String token, int diasTotais, boolean situacao) {
        this.id = id;
        this.idAlojamento = alojamento;
        this.alojamentoTitle = alojamentoTitle;
        this.pet = pet;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.valor = valor;
        this.tipo = tipo;
        this.token = token;
        this.diasTotais = diasTotais;
        //this.situacao = situacao;
    }
    //pr.getAlojamentoId(), pr.getAlojamentoTitle(),pr.getPetId(), "RESERVADO", pr.getCheckin(), pr.getCheckout(), Double.valueOf(t), tipo, token, totalDias)
    //alojamento, titulo alojoamento, id pet, status, checkin, checkou, valor, tipo, token, total dias
    public Reserva(int alojamento, String alojamentoTitle, int pet, String status, Date checkin, Date checkout, Double valor, int tipo, String token, int diasTotais, String canil) {
        this.idAlojamento = alojamento;
        this.alojamentoTitle = alojamentoTitle;
        this.pet = pet;
        this.status = status;
        this.checkin = checkin;
        this.checkout = checkout;
        this.valor = valor;
        this.tipo = tipo;
        this.token = token;
        this.diasTotais = diasTotais;
        this.canil = canil;
        this.situacao = false;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAlojamento() {
        return idAlojamento;
    }

    public void setAlojamento(int idAlojamento) {
        this.idAlojamento = idAlojamento;
    }

    public int getPet() {
        return pet;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String[] getValues(){
        String[] output = {
            idAlojamento + "",
            pet + "",
            status,
            DateTools.dateToSQL(checkin),
            DateTools.dateToSQL(checkout),
            valor + "",
            tipo + "",
            token,
            diasTotais + ""
        };
        return output;
    }

    public String getAlojamentoTitle() {
        return alojamentoTitle;
    }

    public void setAlojamentoTitle(String alojamentoTitle) {
        this.alojamentoTitle = alojamentoTitle;
    }
        
    public String getResume(){
        String a = DateTools.dateToString(checkin);
        String b = DateTools.dateToString(checkout);
        return alojamentoTitle + " - " + canil + " - Pet id: " + pet + " - " + a + "       " + b;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getDiasTotais() {
        return diasTotais;
    }

    public void setDiasTotais(int diasTotais) {
        this.diasTotais = diasTotais;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCanil() {
        return canil;
    }

    public void setCanil(String canil) {
        this.canil = canil;
    }

    public String getCheckinString(){
        return DateTools.dateToString(checkin);
    }
    
    public String getCheckoutString(){
        return DateTools.dateToString(checkout);
    }
    
    public String getTotal(){
        return doubleToString(valor * diasTotais);
    }
    
    public String getValorString(){
        return doubleToString(valor);
    }
    
    public String doubleToString(double valor) {
        String a = valor + "";
        int b = (int)valor;
        String c = b + "";
        String d = a.substring(c.length()+1);
        int e = Integer.parseInt(d);
        
        if(e <= 9){
            return valor + "0";
        } else {
            return valor + "";
        }
    }
    
    public void setSituacao(boolean situacao){
        this.situacao = situacao;
    }
    
    public boolean isSituacao(){
        return this.situacao;
    }
    
    
}
