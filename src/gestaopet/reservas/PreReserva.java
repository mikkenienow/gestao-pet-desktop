
package gestaopet.reservas;

import gestaopet.classes.DateTools;
import java.util.Date;

public class PreReserva {
    private int index;
    private int grupoId;
    private int alojamentoId;
    private String alojamentoTitle;
    private int petId;
    private Date checkin;
    private Date checkout;

    public PreReserva(int index, int grupoId, int alojamentoId, String alojamentoTitle, int petId, Date checkin, Date checkout) {
        this.index = index;
        this.grupoId = grupoId;
        this.alojamentoId = alojamentoId;
        this.alojamentoTitle = alojamentoTitle;
        this.petId = petId;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setCheckout(Date checkout) {
        this.checkout = checkout;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getGrupoId() {
        return grupoId;
    }

    public void setGrupoId(int grupoId) {
        this.grupoId = grupoId;
    }

    public int getAlojamentoId() {
        return alojamentoId;
    }

    public void setAlojamentoId(int alojamentoId) {
        this.alojamentoId = alojamentoId;
    }

    public Date getCheckin() {
        return checkin;
    }

    public void setCheckin(Date checkin) {
        this.checkin = checkin;
    }
    
    public String getResume(){
        return "Index; " + index + " Grupo:" + grupoId  + " Alojamento:" + alojamentoId  + " D i " + DateTools.dateToString(checkin)  + " D f " +  DateTools.dateToString(checkout);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getAlojamentoTitle() {
        return alojamentoTitle;
    }

    public void setAlojamentoTitle(String alojamentoTitle) {
        this.alojamentoTitle = alojamentoTitle;
    }
    
}


/*    private int index;
    private int grupoId;
    private int alojamentoId;
    private Date dataInicial;
    private Date dataFinal;
*/