package gestaopet.classes;

import gestaopet.enums.PetSize;
import gestaopet.enums.StatusAlojamento;

public class Alojamento {
    private int id;
    private String titulo;
    private StatusAlojamento status = StatusAlojamento.LIVRE;
    
    public Alojamento(int id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StatusAlojamento getStatus() {
        return status;
    }

    public void setStatus(StatusAlojamento status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
