package gestaopet.classes;

public class Pessoa {
    private int id;
    private String tipo;
    private String doc;
    private String nome1;
    private String nome2;
    private String email;
    private String tel1;
    private String tel2;
    private String tel3;
    private String endereco;
    private String cidade;
    private String estado;
    private String obs;

    public Pessoa(int id, String tipo, String doc, String nome1, String nome2, String email, String tel1, String tel2, String tel3, String endereco, String cidade, String estado, String obs) {
        this.id = id;
        this.tipo = tipo;
        this.doc = doc;
        this.nome1 = nome1;
        this.nome2 = nome2;
        this.email = email;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.obs = obs;
    }
    
    public Pessoa(String tipo, String doc, String nome1, String nome2, String email, String tel1, String tel2, String tel3, String endereco, String cidade, String estado, String obs) {

        
        this.tipo = tipo;
        this.doc = doc;
        this.nome1 = nome1;
        this.nome2 = nome2;
        this.email = email;
        this.tel1 = tel1;
        this.tel2 = tel2;
        this.tel3 = tel3;
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.obs = obs;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getNome1() {
        return nome1;
    }

    public void setNome1(String nome1) {
        this.nome1 = nome1;
    }

    public String getNome2() {
        return nome2;
    }
    
    public String getNomeCompleto(){
        return nome1 + " " + nome2;
    }
    
    public void setNome2(String nome2) {
        this.nome2 = nome2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel1() {
        return tel1;
    }

    public void setTel1(String tel1) {
        this.tel1 = tel1;
    }

    public String getTel2() {
        return tel2;
    }

    public void setTel2(String tel2) {
        this.tel2 = tel2;
    }

    public String getTel3() {
        return tel3;
    }

    public void setTel3(String tel3) {
        this.tel3 = tel3;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public String[] getValues(){
        String[] output = {
            tipo,
            doc,
            nome1,
            nome2,
            email,
            tel1,
            tel2,
            tel3,
            endereco,
            cidade,
            estado,
            obs
        };
        return output;
    }
}
