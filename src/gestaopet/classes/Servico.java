package gestaopet.classes;

import java.util.Date;

public class Servico {
    private int id;
    private String servico;
    private Date data;
    private int pet;
    private double valor;
    private boolean lembrete;
    private boolean situacao;
    
            
    public Servico(String servico, Date data, int pet, double valor, boolean lembrete, boolean situacao){
        this.servico = servico;
        this.data = data;
        this.pet = pet;
        this.valor = valor;
        this.lembrete = lembrete;
        this.situacao = situacao;
    }
    
    public Servico(int id, String servico, Date data, int pet, double valor, boolean lembrete, boolean situacao){
        this.id = id;
        this.servico = servico;
        this.data = data;
        this.pet = pet;
        this.valor = valor;
        this.lembrete = lembrete;
        this.situacao = situacao;
    }

    public Servico(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getServicoString() {
        
        switch(this.servico){
            case "banho":
                return "Banho";
            case "creche":
                return "Creche";
            case "banhoetosa":
                return "Banho & Tosa";
        }
        return "Não definido";
    }
    public String getServico() {
        return this.servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPet() {
        return pet;
    }

    public void setPet(int pet) {
        this.pet = pet;
    }

    public double getValor() {
        return valor;
    }
    
    public String getStringValor() {
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

    public void setValor(double valor) {
        this.valor = valor;
    }

    public boolean isLembrete() {
        return lembrete;
    }

    public void setLembrete(boolean lembrete) {
        this.lembrete = lembrete;
    }
    
        public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public String getResume(){
        String[] o = getValues();
        return o[0] + " " + o[1] + " " + o[2] + " " + o[3] + " " + o[4] + " " + o[5] + " " + o[6];
    }
    
    
    public String[] getValues(){
        String[] output = {
            servico,
            DateTools.dateToSQL(data),
            DateTools.timeToSQL(data),
            pet + "",
            valor + "",
            (lembrete)? "1" : "0",
            (situacao)? "1" : "0"         
        };
        return output;
    }



    
    
}
