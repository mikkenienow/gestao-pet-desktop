package gestaopet.reservas;

import gestaopet.classes.DateTools;
import java.util.Date;

public class Disponibilidade {
    private Date data;
    private int idAlojamento;
    private String alojamentoTitle;
    private boolean disponivel = true;

    public Disponibilidade(Date data, String alojamentoTitle, int idAlojamento) {
        this.data = data;
        this.alojamentoTitle = alojamentoTitle;
        this.idAlojamento = idAlojamento;
    }

    public int getIdAlojamento() {
        return idAlojamento;
    }

    public void setReserva(int idAlojamento) {
        this.idAlojamento = idAlojamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
       
    public String getResume(){
        String d = DateTools.dateToString(data);
        
        return d + " " + idAlojamento + ((disponivel)? " Disponível ": " indisponivel");
    }

    public String getAlojamentoTitle() {
        return alojamentoTitle;
    }

    public void setAlojamentoTitle(String alojamentoTitle) {
        this.alojamentoTitle = alojamentoTitle;
    }

}
