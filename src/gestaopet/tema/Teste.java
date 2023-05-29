package gestaopet.tema;

import gestaopet.classes.DateTools;
import gestaopet.classes.Canil;
import gestaopet.classes.Pet;
import gestaopet.enums.DateMethods;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
Sistema > Cria lista de datas do range

Banco   > Encontra todos os alojamentos disponível data por data

Sistema > Cria lista de resultado
	
	Cria grupos de ranges sem quebra
	


	Verifica data por data a quantidade de alojamento por data
		
		Se alguma data não possuir NENHUM alojamento, cancela operação e retorna as datas que não tem disponibilidade.
		Senão 
*/
public class Teste {
    //List<Object>
    Pet pet;
    String[][] canis = {{"01", "Grande"},{ "02", "Pequeno"}, {"03","Pequeno"}, {"04","Grande"}, {"05","Pequeno"}};
    String[][] alojamentos = {
        {"01", "A01","A02","A03","A04","A05"},
        {"02", "A01","A02","A03","A04","A05"},
        {"03", "A01","A02","A03","A04","A05"},
        {"04", "A01","A02","A03","A04","A05"},
        {"05", "A01","A02","A03","A04","A05"}};
    
    String[][] reservas = {
        {"A01", "01", "08/02/2023", "15/02/2023"},
        {"A02", "01", "18/01/2023", "26/01/2023"},
        {"A03", "01", "23/01/2023", "31/01/2023"},
        {"A04", "01", "10/02/2023", "17/02/2023"},
        {"A05", "01", "29/01/2023", "03/02/2023"},

        {"A01", "02", "06/01/2023", "07/02/2023"},
        {"A02", "02", "11/01/2023", "13/01/2023"},
        {"A03", "02", "03/01/2023", "08/01/2023"},
        {"A03", "02", "14/01/2023", "17/01/2023"},
        {"A04", "02", "05/01/2023", "09/02/2023"},
        {"A05", "02", "08/01/2023", "16/01/2023"},

        {"A01", "03", "08/02/2023", "11/02/2023"},
        {"A02", "03", "11/02/2023", "15/02/2023"},
        {"A03", "03", "05/02/2023", "08/02/2023"},
        {"A04", "03", "25/01/2023", "31/01/2023"},
        {"A05", "03", "17/01/2023", "18/01/2023"},
    };
    
    Date checkin;
    Date checkout;
    List<Date> dataL;
    List<String[]> canilL;
    List<String[]> disponibilidadeL;
    List<String[]> listI  = new ArrayList<>();
    List<String[]> reservaefetiva = new ArrayList<>();
    String[] alojamentoL;
    String canil;
    
    
    public void testar(){
        Pet novoPet = new Pet();
        novoPet.setNome("Luluzinha");
        novoPet.setStringPorte("Pequeno");
        novoPet.setId(1);

        iniciarAgendamento(novoPet);
        dataL = definirDatas("01/01/2023","17/01/2023");
        canilL = pegaCanis();
        canil = canilL.get(0)[0];
        System.out.println("Canil escolhido: " + canil);
        alojamentoL = pegaAlojamentos();
        
        
        disponibilidadeL = alojamentosIndisponiveis();
        
        
        
        for(int i =0; i < disponibilidadeL.size(); i++){
            String a = disponibilidadeL.get(i)[0];
            String b = disponibilidadeL.get(i)[1];
            String d = disponibilidadeL.get(i)[2];
            System.out.println("Alojamento: " + a + " Canil " + b + " no dia " + d);
        }
        try {
            criarGrupos();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    public void iniciarAgendamento(Pet pet){
        this.pet = pet;
        System.out.println("Pet escolhido " + pet.getNome());
    }
    
    public List<String[]> pegaCanis(){
        String porte = this.pet.getPorte()+"";
        System.out.println("O Porte do Pet é : " + porte);
        List<String[]> output = new ArrayList<>();
        
        for(String[] i: canis){
            if(i[1].equals(porte)){
                output.add(i);
                System.out.println("Canil: " + i[0]);
            }
        }
        return output;
    }
    
    public List<Date> definirDatas(String checkin, String checkout){
        this.checkin = DateTools.stringToDate(checkin, "00:00");
        this.checkout = DateTools.stringToDate(checkout, "00:00");
        List<Date> output = new ArrayList<>();
        output.add(this.checkin);
        int diastotais = DateTools.daysBetween(this.checkin, this.checkout, true);
        for(int i =1; i <= diastotais; i++){
            Date d = DateTools.dateIncrease(this.checkin, i);
            output.add(d);
        }
        return output;
    }
    
    public String[] pegaAlojamentos(){
        //pega os alojamentos do canil escolhido
        for(int i = 0; i < alojamentos.length; i++){
            if(alojamentos[i][0].equals(canil)){
                return alojamentos[i];
            }
        }
        return null;
    }
    
    public List<String[]>alojamentosIndisponiveis(){
        List<String[]> output = new ArrayList<>();
        for(int i = 0; i < dataL.size(); i++){
            String d = DateTools.dateToString(dataL.get(i));
            for(int j = 0; j < reservas.length; j++){
                boolean t = DateTools.isBetween(reservas[j][2], reservas[j][3], d);
                if((reservas[j][1].equals(canil) && !t)){
                    output.add(new String[]{ reservas[j][0], reservas[j][1], d});
                }
            }
        }
        
        
        
        
    return output;
    }
    
    public List<String[]> disponiveisReais(){
        List<String[]> output  = new ArrayList<>();
        for(int j = 1; j < alojamentoL.length; j++){
            for(int i = 0; i < disponibilidadeL.size(); i++){
                if(alojamentoL[j].equals(disponibilidadeL.get(i)[0])){
                    output.add(disponibilidadeL.get(i));
                }
            }
        }
        return output;
    }

    public List<String[]> listarDatas(){
        List<String[]> output  = new ArrayList<>();
        for(int i = 0; i < dataL.size(); i++){
            String d = DateTools.dateToString(dataL.get(i));
            for(int j = 0; j < reservas.length; j++){
                boolean t = DateTools.isBetween(reservas[j][2], reservas[j][3], d);
                if(reservas[j][1].equals(canil) && !t){
                    String[] l = {d, reservas[j][0], reservas[j][1]};
                    output.add(l);
                }
            }
        }
        
        for(int i = 0; i < output.size(); i++){
            
        }
        
        
        
        
        return output;
    }
    
    public List<String[]> criarGrupos(){
        List<String[]> output = new ArrayList<>();
        List<String[]> pregroup  = disponiveisReais();
        listI  = pegarGrupos(pregroup);
        
        for(int i = 0; i < listI.size(); i++){
            String[] a = listI.get(i);
            System.out.println("Listando grupos: " + a[0] + " - " + a[1] + " - " + a[2] + " - " + a[3]);
        }

        try {
            pegarMaior(DateTools.dateToString(this.checkin));
        } catch (Exception e) {
        }
        


        //pegar maior
        //String[] reserva = pegarMaior(group);

        //procura preencher
        System.out.println("criar grpos()  -  1");
        System.out.println(" Total " + reservaefetiva.size());
        for(int i = 0; i < reservaefetiva.size(); i++){
            
            String[] n = reservaefetiva.get(i);
            System.out.println("" + n[0] + " " + n[1] + " " + n[2] + " " + n[3]);
        }
        return null;
    }

    public List<String[]> pegarGrupos(List<String[]> pregroup){
        List<String[]> output  = new ArrayList<>();
        int groupIndex = 0;
        String alojamento = pregroup.get(0)[0];
        String data = pregroup.get(0)[2];
        int diasTotais = 0;

        for(int i = 0; i < pregroup.size(); i++){
            try {
                if(i==0){
                    output.add(new String[] {"" + groupIndex, alojamento, data, "" + diasTotais});
                    diasTotais ++;
                } else {
                    Date d = DateTools.stringToDate(pregroup.get(i)[2], "00:00");
                    Date d2 = DateTools.stringToDate(pregroup.get(i+1)[2], "00:00");
                    boolean diaSubsequente = DateTools.compareDates(DateTools.dateIncrease(d, 1), d2);
                    System.out.println("Dia subsequente" + diaSubsequente);
                    if(diaSubsequente && pregroup.get(i)[0].equals(pregroup.get(i+1)[0])){
                        diasTotais ++;
                        output.get(groupIndex)[3] = diasTotais + "";
                    } else {
                        diasTotais = 0;
                        groupIndex ++;
                        String al2 = pregroup.get(i+1)[0];
                        String dt2 = pregroup.get(i+1)[2];
                        String[] n2 = {"" + groupIndex, al2, dt2, diasTotais + ""};
                        output.add(n2);
                    }
                }
            } catch (Exception e) {
                System.out.println("pegarGrupos() " + e);
            }
        }
        return output;
    }
    

    public void pegarMaior(String dataReferencia){
            int maior = 0;
            String[] temp = null;
            String[] reserva = null;
            String dataFinal = "";
            String proxData = DateTools.dateToString(DateTools.dateIncrease(dataReferencia,1));
            //System.out.println("Prox data: " + proxData);
            if(!proxData.equals(DateTools.dateToString(this.checkout))){
                for(int i = 0; i < listI.size(); i++){
                    if(verificarLista(listI.get(i)[0])){
                        String dataInicial = listI.get(i)[2];
                        //System.out.println("A data inicial do " + listI.get(i)[0] + " é " + dataInicial);
                        if(dataReferencia.equals(dataInicial)){
                            //System.out.println("pegarMaior()  -  a data inicial do grupo é igual a " + dataReferencia);
                            int testar = Integer.parseInt(listI.get(i)[3]);
                            if(testar > maior){
                                //System.out.println("pegarMaior()  -  encontrou o maior valor");
                                maior = testar;
                                temp = listI.get(i);
                                Date d1 = DateTools.stringToDate(DateTools.dateToString(DateTools.dateIncrease(dataInicial,maior)), "00:00");
                                Boolean c = DateTools.dateCompare(d1, checkout, true);
                                dataFinal = DateTools.dateToString((c)? d1: checkout);
                                System.out.println("Data final: " + dataFinal);
                                reserva = new String[]{ 0 + "",listI.get(i)[0] ,listI.get(i)[1], dataInicial, dataFinal};
                            }
                        }
                        if(i == listI.size()-1){
                            reservaefetiva.add(reserva);// adiciona valor encontrado na lista final
                            System.out.println("pegarMaior()  -  Adicionando na Reserva efetiva " + reserva[1]);
                        }
                    }
                }
            }
            
            
            
            System.out.println("Data final é:  " + dataFinal);
            System.out.println("");
            System.out.println("");
            System.out.println("");
            System.out.println("");
            
            proxData = DateTools.dateToString(DateTools.dateIncrease(dataFinal,1));
            if(!proxData.isEmpty() && !dataFinal.equals(DateTools.dateToString(checkout))){
                prepareGroup(proxData);
                pegarMaior(proxData);
            }
    }
    
    public boolean verificarLista(String grupoId){
        
        for(int i = 0; i < reservaefetiva.size(); i++){
            if(reservaefetiva.get(i)[0].equals(grupoId)){
                return false;
            }
        }
        return true;
    }
    
    
    
    public void prepareGroup(String date){
        System.out.println("Testando data: " + date);
        for(int i = 0; i < listI.size(); i++){
            String d1 = listI.get(i)[2];
            String d2 = DateTools.dateToString(DateTools.dateIncrease(d1, Integer.parseInt(listI.get(i)[3])));
            System.out.println("Data do grupo " + listI.get(i)[0]); 
            int total = DateTools.daysBetween(DateTools.stringToDate(listI.get(i)[2], "00:00"),
                    DateTools.dateIncrease(d1, Integer.parseInt(listI.get(i)[3])),
                    true);
            
            boolean t = DateTools.isBetween(d1, d2, date);
            if(t){
                listI.get(i)[2] = date;
                listI.get(i)[3] = (total) + "";
                System.out.println("Agora a data do grupo " + listI.get(i)[0] + " é " + d1 + " total do grupo é " + listI.get(i)[3]); 
            }
        }
    }
    
}


