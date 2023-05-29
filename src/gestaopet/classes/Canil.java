package gestaopet.classes;

import gestaopet.DB.AlojamentoDB;
import gestaopet.enums.PetSize;
import gestaopet.enums.StatusAlojamento;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Canil {
    private int id;
    private String titulo;
    private PetSize porte;
    private List<Alojamento> alojamentos = new ArrayList<>();


    public void setPorte(PetSize porte) {
        this.porte = porte;
    }
    
    

    public Canil(int id, String title, String porte){
        this.id = id;
        this.titulo = title;
        this.porte = setStringPorte(porte);
        setSectionList();
    }
    
    public PetSize getPorte() {
        return porte;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Alojamento> getAlojamentoList() {
        return alojamentos;
    }
    
    public Alojamento getAlojamento(int pos){
            return this.alojamentos.get(pos);
    }
    
    public Alojamento getAlojamentoById(int id){
        
        for(int i = 0; i < getTotalSections(); i++){
            int a = alojamentos.get(i).getId();
            if(a == id){
                return alojamentos.get(i);
            }
        }
        return null;
    }
    
    

    public void addSection(Alojamento h) {
        this.alojamentos.add(h);
    }
    
    public int getTotalSections(){
         return this.alojamentos.size();
    }
    
    public PetSize setStringPorte(String porte){
        switch(porte.toLowerCase()){
            case "pequeno":
                return PetSize.Pequeno;
            case "medio":
                return PetSize.Medio;
            case "grande":
                return PetSize.Grande;
        }
        return null;
    }
    
    public void setSectionList(){
        alojamentos.clear();
        alojamentos = AlojamentoDB.getByCanil(id);
    }
    
    public void setSectionListByDate(Date i, Date f){
        List<Alojamento> list = new ArrayList<>();
        list = AlojamentoDB.getByDateCanil(id, i, f);
        for(int j = 0; j < alojamentos.size(); j++){
            Alojamento a = alojamentos.get(j);
            a.setStatus(StatusAlojamento.RESERVADO);
            for(int k =0; k < list.size(); k++){
                Alojamento b = list.get(k);
                if(a.getId() == b.getId()){
                    alojamentos.get(j).setStatus(StatusAlojamento.LIVRE);
                }
            }
        }
    }
    
    public boolean isIndisponivel(){
        for(int i = 0; i < alojamentos.size(); i++){
            if(alojamentos.get(i).getStatus().equals(StatusAlojamento.LIVRE)){
                return false;
            }
        }
        return true;
    }
    
}
