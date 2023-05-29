package gestaopet.classes;

import java.util.Date;

public class Vacina {
    private int idvacina;
    private String nome;
    private int idpet;
    private String data;

    public Vacina(int idvacina, String nome, int idpet, String data) {
        this.idvacina = idvacina;
        this.nome = nome;
        this.idpet = idpet;
        this.data = data;
    }
    
    public Vacina(String nome, int idpet, String data) {
        this.nome = nome;
        this.idpet = idpet;
        this.data = data;
    }

    public String getData() {
        if(!this.data.isEmpty()){
            return DateTools.stringDateToSQL(data);
        }
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getIdvacina() {
        return idvacina;
    }

    public void setIdvacina(int idvacina) {
        this.idvacina = idvacina;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String[] getValues(){
        return new String[] {nome, idpet+"", getData()};
    }
    
}
