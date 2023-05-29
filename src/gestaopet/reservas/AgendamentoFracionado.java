package gestaopet.reservas;

import gestaopet.DB.CanilDB;
import gestaopet.DB.ReservaDB;
import gestaopet.classes.Alojamento;
import gestaopet.classes.Canil;
import gestaopet.classes.DateTools;
import gestaopet.classes.Pet;
import gestaopet.classes.Reserva;
import gestaopet.enums.DateMethods;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgendamentoFracionado {
    private Pet pet;
    private Canil canil;
    private Date checkin;
    private Date checkout;
    private List<String[]> listI  = new ArrayList<>();
    private List<Alojamento> alojamentoL;
    private List<Reserva> reservas = new ArrayList<>();
    private List<Canil> canis = new ArrayList<>();
    private List<Date> dataL = new ArrayList<>();
    private List<Disponibilidade> disponibilidadeL = new ArrayList<>();
    private List<GrupoAlojamento> grupos  = new ArrayList<>();
    private List<PreReserva> reservaefetiva = new ArrayList<>();
    private boolean success = false;
    
    public AgendamentoFracionado(){
        
    }
    
    private void definirCheckinCheckout(String in, String out){
        this.checkin = DateTools.stringToDate(in,"00:00");
        this.checkout = DateTools.stringToDate(out,"00:00");
        dataL.add(this.checkin);
        int diastotais = DateTools.daysBetween(this.checkin, this.checkout, true);
        for(int i =1; i <= diastotais; i++){
            Date d = DateTools.dateIncrease(this.checkin, i);
            dataL.add(d);
        }
    }
    
    public List<PreReserva> getReserva(){
        return reservaefetiva;
    }
    
    private void definirCanil(int pos){
        this.canil = canis.get(pos);
        this.alojamentoL = canil.getAlojamentoList();
    }
    
    private void filtrarIndisponiveis(){
        List<Disponibilidade> disponibilidade = new ArrayList<>();
        this.disponibilidadeL.clear();
        for(int i = 0; i < dataL.size(); i++){
            Date d = dataL.get(i);
            for(int j = 0; j < canil.getTotalSections(); j ++){
                disponibilidade.add(new Disponibilidade(d, canil.getAlojamentoList().get(j).getTitulo(), canil.getAlojamentoList().get(j).getId()));
            }
        }
        
        for(int i = 0; i < disponibilidade.size(); i++){
            Date d = disponibilidade.get(i).getData();
            int alId = disponibilidade.get(i).getIdAlojamento();
            for(int j = 0; j < reservas.size(); j++){
                if(reservas.get(j).getIdAlojamento() == disponibilidade.get(i).getIdAlojamento()){
                    boolean t = DateTools.isBetween(reservas.get(j).getCheckin(), reservas.get(j).getCheckout(), d);
                    if(t){
                        disponibilidade.get(i).setDisponivel(false);
                    }
                }
            }
        }
        
        for(int i = 0; i < canil.getAlojamentoList().size(); i++){
            for(int j = 0; j < disponibilidade.size(); j++){
                if(canil.getAlojamentoList().get(i).getId() == disponibilidade.get(j).getIdAlojamento()){
                    disponibilidadeL.add(disponibilidade.get(j));
                }
            }
        }

    }
    
    private void pegarGrupos(){
        grupos.clear();
        int groupIndex = 0;
        int diasTotais = 0;
        boolean primeiro = true;

        
        for(int i = 0; i < disponibilidadeL.size(); i++){
            try {

                if(primeiro){
                    diasTotais ++;

                    groupAdd(new GrupoAlojamento(0,disponibilidadeL.get(i), diasTotais));

                    primeiro = false;
                } else {
                    Date d = disponibilidadeL.get(i).getData();
                    boolean diafinal = DateTools.compareDates(d, this.checkout);
                    Date d2 = DateTools.dateIncrease(d, 1);
                    boolean diaSubsequente = false;
                    boolean id = false;
                    boolean disponivel = disponibilidadeL.get(i).isDisponivel();
                    try {
                        d2 = disponibilidadeL.get(i+1).getData();
                        id = disponibilidadeL.get(i).getIdAlojamento() == disponibilidadeL.get(i+1).getIdAlojamento();
                    } catch (Exception e) {
                    }
                    
                    diaSubsequente = DateTools.compareDates(DateTools.dateIncrease(d, 1), d2);
                    
                    if(diaSubsequente && id && disponivel){
                        diasTotais ++;
                        grupos.get(groupIndex).setDias(diasTotais);
                       
                    } else {
                        String a = DateTools.dateToString(d);
                        String b = DateTools.dateToString(this.checkout);
                        boolean testecheckout = DateTools.compareDates(d, this.checkout);
                        
                        if(testecheckout && disponivel){
                            diasTotais++;
                            grupos.get(groupIndex).setDias(diasTotais);
                            
                        }
                        diasTotais = 0;
                        groupIndex++;
                        GrupoAlojamento n2 = new GrupoAlojamento(groupIndex,disponibilidadeL.get(i+1), diasTotais);
                        groupAdd(n2);

                    }
                }
            } catch (Exception e) {
                System.out.println("Erro " + e);
            }
        }
        /*
        for(int i = 0; i < grupos.size(); i++){
            if(grupos.get(i).getDisponibilidade().isDisponivel()){
                
            }
        }*/
    }
    
    private void groupAdd(GrupoAlojamento grupo){
        int count = 0;
        for(int i = 0; i < grupos.size(); i++){
            if(grupos.get(i).getIndex() == grupo.getIndex()){
                count ++;
            }
        }
        if(count == 0){
            
            grupos.add(grupo);
        }
    }
    
    private void pegarMaior(Date dataReferencia){
        int maior = 0;
        GrupoAlojamento temp = null;
        PreReserva reserva = null;
        Date dataFinal = DateTools.getDate(DateMethods.NEWDAY, 9999, 1, 1);
        Date proxData = DateTools.dateIncrease(dataReferencia,1);
        

        
        if(!DateTools.compareDates(dataReferencia, this.checkout)){
            for(int i = 0; i < grupos.size(); i++){

                GrupoAlojamento ga = grupos.get(i);
                if(ga.getDisponibilidade().isDisponivel()){
                    
                    Date dataInicial = ga.getDisponibilidade().getData();

                    if(DateTools.compareDates(dataReferencia, dataInicial)){
                        int testar = grupos.get(i).getDias();
                        if(testar > maior){
                            maior = testar;
                            temp = ga;
                            Date d1 = DateTools.dateIncrease(dataInicial,maior-1);
                            Boolean c = DateTools.dateCompare(d1, checkout, true);
                            dataFinal = (c)? d1: checkout;
                            int index = reservaefetiva.size();
                            
                            ga.getDisponibilidade().setDisponivel(false);
                            reserva = new PreReserva(index,ga.getIndex(), ga.getDisponibilidade().getIdAlojamento(), ga.getDisponibilidade().getAlojamentoTitle(), pet.getId(),dataInicial, dataFinal);
                        }
                    }
                }
                    
                    if(i == (grupos.size()-1)){
                        reservaefetiva.add(reserva);// adiciona valor encontrado na lista final
                    }
                
            }
        }


        proxData = DateTools.dateIncrease(dataFinal,1);
        boolean b = DateTools.compareDates(proxData, DateTools.getDate(DateMethods.NEWDAY, 9999, 1, 1));
        boolean c = DateTools.compareDates(dataFinal, checkout);
        if(!b && !c){
            prepareGroup(proxData);
            pegarMaior(proxData);
        }
        Date d1 = reservaefetiva.get(reservaefetiva.size() -1).getCheckout();
        
                
        if(DateTools.compareDates(d1, checkout)){
            success = true;
        } else {
            success = false;
        }
    }
    
    private boolean verificarLista(int grupoId){
        for(int i = 0; i < reservaefetiva.size(); i++){
            if(reservaefetiva.get(i).getGrupoId() == grupoId){
                return false;
            }
        }
        return true;
    }
    
    private void prepareGroup(Date date){
        for(int i = 0; i < grupos.size(); i++){
            if(grupos.get(i).getDisponibilidade().isDisponivel()){
                
                Date d1 = grupos.get(i).getDisponibilidade().getData();
                Date d2 = DateTools.dateIncrease(d1, grupos.get(i).getDias());

                boolean t = DateTools.isBetween(d1, d2, date);
                if(t){
                    int total = DateTools.daysBetween(date,d2,true);
                    grupos.get(i).getDisponibilidade().setData(date);
                    grupos.get(i).setDias(total);
                }
            }
        }
    }    
        
    //populando
    public List<PreReserva> reservando(Pet pet, int canil, String checkin, String checkout){
        this.pet = pet;
        pegarCanis();
        definirCheckinCheckout(checkin,checkout);
        definirCanil(canil);
        pegarReservas();
        filtrarIndisponiveis();
        pegarGrupos();
        try {
            pegarMaior(this.checkin);
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        
        if(!success){
            reservaefetiva.clear();
        }
        return reservaefetiva;
    }
    
    private void pegarReservas(){
        reservas = ReservaDB.getByDate(checkin, checkout, canil.getId());
    }
    
    private void pegarCanis(){
        
        this.canis = CanilDB.getByPorte(this.pet.getPorte());
    }
}
