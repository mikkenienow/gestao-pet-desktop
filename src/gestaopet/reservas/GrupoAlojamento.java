package gestaopet.reservas;

public class GrupoAlojamento {
    private int index;
    private Disponibilidade disponibilidade;
    private int dias;

    public GrupoAlojamento(int index, Disponibilidade disponibilidade, int dias) {
        this.index = index;
        this.disponibilidade = disponibilidade;
        this.dias = dias;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Disponibilidade getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Disponibilidade disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    
    public String getResume(){
        return  disponibilidade.getIdAlojamento() + " Dias disponíveis: " + dias;
    }

}
