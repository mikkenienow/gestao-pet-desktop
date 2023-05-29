package gestaopet.classes;

import gestaopet.enums.DateMethods;
import gestaopet.enums.PetSize;
import java.util.Date;

public class Pet {
    private int id;
    private String nome;
    private String raca;
    private Date nascimento;
    private String genero;
    private PetSize porte;
    private boolean sociavel;
    private boolean castrado;
    private String foto;
    private String acessorio;
    private String obs;
    private int donoId;
    private String donoNome;
    
    public Pet(){
        
    }

    public Pet(int id, String nome, String raca, Date nascimento, String genero, String porte, boolean sociavel, boolean castrado, String foto, String acessorio, String obs, String donoNome, int donoId) {
        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.nascimento = nascimento;
        this.genero = genero;
        setStringPorte(porte);
        this.sociavel = sociavel;
        this.castrado = castrado;
        this.foto = foto;
        this.acessorio = acessorio;
        this.obs = obs;
        this.donoNome = donoNome;
        this.donoId = donoId;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public PetSize getPorte() {
        return porte;
    }

    public void setPorte(PetSize porte) {
        this.porte = porte;
    }

    public boolean isSociavel() {
        return sociavel;
    }

    public void setSociavel(boolean socialibilidade) {
        this.sociavel = socialibilidade;
    }

    public boolean isCastrado() {
        return castrado;
    }

    public void setCastrado(boolean castrado) {
        this.castrado = castrado;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(boolean foto) {
        if(foto){
            this.foto =  nomeFoto();
        } else {
            this.foto = "none";
        }
    }
    
    public void setFoto(String foto) {
        this.foto =  foto;

    }
    public String getAcessorio() {
        return acessorio;
    }

    public void setAcessorio(String acessorio) {
        this.acessorio = acessorio;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getDonoId() {
        return donoId;
    }

    public void setDonoId(int donoId) {
        this.donoId = donoId;
    }
    
    public String nomeFoto(){
        String hoje = DateTools.dateToString(DateTools.getDate(DateMethods.TODAY, id, id, id));
        String nasc = DateTools.dateToString(this.nascimento);
        String output = "" + hoje + nasc + this.nome;
        output = output.replaceAll("/", "");

        return output;
    }
    
    public String[] getValues(){
        String[] output = {
            nome,
            raca,
            DateTools.dateToSQL(this.nascimento),
            genero,
            porte + "",
            (sociavel)? "1" : "0",
            (castrado)? "1" : "0",
            foto,
            acessorio,
            obs,
            donoId + ""            
        };
        return output;
    }

    public String getDonoNome() {
        return donoNome;
    }
    
    public void setStringPorte(String porte){
        switch(porte.toLowerCase()){
            case "pequeno":
                this.porte = PetSize.Pequeno;
                break;
            case "medio":
                this.porte =  PetSize.Medio;
                break;
            case "grande":
                this.porte =  PetSize.Grande;
                break;
        }
    }
    
}