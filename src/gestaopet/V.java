
package gestaopet;

import gestaopet.view.pessoa.ListarPessoa;
import gestaopet.view.pessoa.RegistrarPessoa;
import gestaopet.view.pet.ListarPet;
import gestaopet.view.home.StartPage;
import gestaopet.components.modal.SpecialModal;
import gestaopet.view.agendamento.reserva.ListarCanil;
import gestaopet.view.agendamento.general.Calendar;
import gestaopet.view.servicos.PainelServico;
import gestaopet.classes.Navegation;
import gestaopet.classes.Pet;
import gestaopet.view.pet.RegistrarPet;
import gestaopet.components.modal.Modal;
import gestaopet.view.New.estoque.ListarEstoque;
import gestaopet.view.agendamento.reserva.ReservaWizard;
import gestaopet.view.agendamento.reserva.ShowMessage;
import gestaopet.view.New.estoque.RegistrarItem;
import gestaopet.view.New.financeiro.ListarFinanceiro;
import gestaopet.view.home.Home;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;


public class V {
    //Variáveis para uso global
    public static String e = "!#!";
    public static Navegation nav = GestaoPet.init.nav;
    public static Home home = GestaoPet.init;
    public static StartPage start = (StartPage)GestaoPet.init.nav.getPanel(0);
    public static ListarPet pets = (ListarPet)GestaoPet.init.nav.getPanel(1);
    public static RegistrarPet petReg = (RegistrarPet)GestaoPet.init.nav.getPanel(2);
    public static ListarPessoa pessoa = (ListarPessoa)GestaoPet.init.nav.getPanel(3);
    public static ListarFinanceiro fin = (ListarFinanceiro)GestaoPet.init.nav.getPanel(13);
    public static ListarCanil dkv = (ListarCanil)GestaoPet.init.nav.getPanel(5);
    public static RegistrarPessoa pessoaReg = (RegistrarPessoa)GestaoPet.init.nav.getPanel(6);
    public static PainelServico serv = (PainelServico)GestaoPet.init.nav.getPanel(7);
    public static Calendar services = (Calendar)GestaoPet.init.nav.getPanel(8);
    public static Calendar booking = (Calendar)GestaoPet.init.nav.getPanel(9);
    public static ReservaWizard bkw = (ReservaWizard)((Calendar)GestaoPet.init.nav.getPanel(9)).function;
    public static RegistrarItem estoque = (RegistrarItem)GestaoPet.init.nav.getPanel(12);
    public static ListarEstoque estoqueLoad = (ListarEstoque)GestaoPet.init.nav.getPanel(11);
    
    
    //testes
    //public static ListarReserva listRes = new ListarReserva(true);

    public static void reloadAllEntityList(){
        pets.loadPetList("","");
        pessoa.loadPersonList("","back");
        
    }
    public static void navigate(int pos){
        nav.setView(pos);
    }
    
    public static void pet(Pet pet){
        petReg.loadPet(pet);
    }
    
    public static Modal newPopUp(JPanel panel){
        return new Modal(panel);
    }

    public static SpecialModal newSpecialModal(JPanel panel, boolean independentLocation){
        return new SpecialModal(panel, independentLocation);
    }
    
    public static void showMessage(String msg){
        showMessage(msg,"");
    }
    
    public static void showMessage(String msg, String botao){
        ShowMessage sm = new ShowMessage();
        if(botao.isEmpty()){
            sm.show(msg);
        } else {
            sm.show(msg, botao);
        }
    }
    
    public static void unlockHome(){
    home.setEnabled(true);
    home.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
/*
            JPanel[] list = {
            new StartPage(),                               //0
            new Pets(1),                          //1
            new PetRegistration(2),                     //2
            new StartPage(),                            //3
            new Financial(),                           //4
            new DogKennelView(),                       //5
            new JPanel(),                              //6
            new JPanel(),                              //7
            new Calendar(CalendarMode.SERVICES),    //8
            new Calendar(CalendarMode.BOOKING)};    //9
        return list;
    */
    
    
    
    
}
