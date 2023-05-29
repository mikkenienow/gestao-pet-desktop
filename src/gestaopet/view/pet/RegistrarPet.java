package gestaopet.view.pet;

import gestaopet.components.ImageStore;
import gestaopet.view.servicos.PainelServico;
import gestaopet.DB.PessoaDB;
import gestaopet.DB.PetDB;
import gestaopet.V;
import gestaopet.classes.DateTools;
import gestaopet.classes.FunctionButton;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.Navegation;
import gestaopet.classes.Pet;
//import gestaopet.view.components.DataPicker_OLD;
import gestaopet.components.DataPicker;
import gestaopet.tema.ComboBox.ComboBox;
import gestaopet.tema.checkbox.CheckBox;
import gestaopet.view.agendamento.general.EscolherTipoServico;
import gestaopet.view.components.vacina.AdicionarVacina;
import gestaopet.view.reservas.PainelReserva;
import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;

public class RegistrarPet extends GlobalPanel {
    private Navegation nv;
    private Navegation nv2;
    private ImageStore is = new ImageStore(false);
    private PainelServico serv = new PainelServico(false);
    private PainelReserva rese = new PainelReserva(true);
    private String param;
    private Date nasc;
    private FunctionButton[] bList;
    int[][] blist;
    private int donoId;
    private Pet pet;
    private boolean petLoaded = false;
    DataPicker sp;
    private CheckBox cbGm;
    private CheckBox cbGf;
    private CheckBox cbPp;
    private CheckBox cbPg;
    private ComboBox cb1;
    
    public RegistrarPet(int param) {
        initComponents();
        panelServicos.setVisible(false);
        this.param = param + "";
        nv = new Navegation(area, target, is);
        nv.setViewPanel(10, 300, 300);
        
        nv2 = new Navegation(servicosPet, target2, this.serv);
        nv2.setViewPanel(10, 400, 400);
        this.serv.setLocation(10, 20);

        cbGm = new CheckBox(male, genero, this);
        cbGf = new CheckBox(female, genero, this);
        ComboBox.setTheme(cb1, tipoServico, new int[] {10,0}, 40,0, panelServicos);

        addComponentTo(cbGm, (List<Object>)getCheckBoxList(), genderPanel);
        addComponentTo(cbGf, (List<Object>)getCheckBoxList(), genderPanel);
        //genderPanel.add(cbGm);
        //genderPanel.add(cbGf);
        cbGm.setLocation(3,15);
        cbGf.setLocation(3,40);
        cbGm.setSelection();
        
        cbPp = new CheckBox(small, porte, this);
        cbPg = new CheckBox(large, porte, this);
        addComponentTo(cbPp, (List<Object>)getCheckBoxList(), portePanel);
        addComponentTo(cbPg, (List<Object>)getCheckBoxList(), portePanel);

        cbPp.setLocation(3,15);
        cbPg.setLocation(3,40);
        cbPp.setSelection();
        
        
        
        
        
        FunctionButton[] ini = {
            new FunctionButton("Salvar", true,"salvarTB1.png","salvarTB0.png", this.param),
            new FunctionButton("Excluir", false,"excluirTB1.png","excluirTB0.png", this.param),
            new FunctionButton("Agendar", false,"agendarTB1.png","agendarTB0.png",  this.param),
            new FunctionButton("Vacinas", false,"vacinasTB1.png","vacinasTB0.png", this.param),
            new FunctionButton("Voltar", true,"icons8_left_2_35px.png", this.param),
        };
        buttonsInit(ini);
    }
    
    public void dataPicker(){
        //DataPicker_OLD sp = new DataPicker_OLD();
        try {
            sp.show(true);
        } catch (Exception e) {
            DataPicker sp = new DataPicker(nascimento);
        }
    }

    public void onLoad(){
        
    }
    @Override
    public void action(ActionEvent evt){
        switch(evt.getActionCommand()){
            case "b1" :     //salvar
                savePet();
                
                break;      //excluir
            case "b2" :
                deleteConfirmation();
                break;
            case "b3" :     //agendar
                newService(this.pet);
                break;
            case "b4" :     //voltar
                AdicionarVacina av = new AdicionarVacina(pet.getId());
                break;
            case "b5" :     //voltar
                back();
                break;
        }
    }
    

    
    public void setDate(Date date){
        nasc = date;
        String nas = DateTools.dateToString(date);
        nascimento.setText(nas);
    }
    
    @Override
    public void delete(){
        PetDB.delete(pet.getId());
        clearAll();
        V.nav.setView(1);
    }
   
    public void savePet(){
        Pet pet = new Pet();
        if(rules()){
            System.out.println("Salvando pet - regras 100%");
            pet.setNome(nome.getText());
            pet.setRaca(raca.getText());
            pet.setNascimento(DateTools.stringToDate(nascimento.getText(), "00:00"));
            
            pet.setGenero(genero.getSelection().getActionCommand());
            pet.setStringPorte(getPorte(porte.getSelection().getActionCommand()));
            System.out.println("Salvando porte: " + porte.getSelection().getActionCommand());
            
            pet.setSociavel(sociavel.isSelected());
            pet.setCastrado(castrado.isSelected());
            
            try {
                if(is.getFoto()){
                pet.setFoto(is.getFoto());
                System.out.println("Salvando pet - tem foto");
                is.loadedImage = null;
                is.copy(pet.getFoto());
            } else {
                pet.setFoto(this.pet.getFoto());
                System.out.println("Salvando pet - já tinha foto");
            }
            } catch (Exception e) {
                pet.setFoto("none");
            }
            
                
            System.out.println("Salvando pet - 01");
            pet.setAcessorio(acessorios.getText());
            pet.setObs(obs.getText());
            pet.setDonoId(donoId);
            System.out.println("Salvando pet - 02");
            if(petLoaded){
                pet.setId(this.pet.getId());
                PetDB.update(pet);
            } else {
                PetDB.insert(pet);
            }
            V.pets.onLoad("","");
            V.nav.setView(1);
            clearAll();
        } else {
            System.out.println("Salvando pet falha nas regras");
        }
    }
    
    
    @Override
    public void clearAll(){
        petLoaded = false;
        setAlert("");
        nome.setText("");
        raca.setText("");
        nasc = null;
        nascimento.setText("");
        setGenero("m");
        setPorte("p");
        sociavel.setSelected(false);
        castrado.setSelected(false);
        is.setBlank();
        acessorios.setText("");
        obs.setText("");
        donoId = 0;
        donoNome.setText("");
        servicosPet.setVisible(petLoaded);
        V.serv.unsetPet();

    }
    
    public void setGenero(String genero){
        if(genero.toLowerCase().indexOf("m") > -1){
            male.setSelected(true);
        } else {
            female.setSelected(true);
        }
    }
    
    public void setPanelServico(Pet pet){
        panelServicos.setVisible(true);
        nv2.remove(this.serv);
        nv2.remove(this.rese);
        if(tipoServico.getSelectedIndex() == 0){
            this.serv = new PainelServico(false);
            this.serv.setPet(pet);
            
            nv2.add(this.serv);
            nv2.setViewPanel(10, 500, 400);
            this.serv.setLocation(10, 20);
            serv.filter();
        } else {
            this.rese = new PainelReserva(true);
            this.rese.setPetId(pet.getId());
            nv2.add(this.rese);
            nv2.setViewPanel(10, 500, 400);
            this.rese.setLocation(20, 20);
        }
    }
    
    
    public void loadPet(Pet pet){
        setPanelServico(pet);
       
        petLoaded = true;
        this.pet = pet;
        nome.setText(pet.getNome());
        raca.setText(pet.getRaca());
        nasc = pet.getNascimento();
        nascimento.setText(DateTools.dateToString(nasc));
        setGenero(pet.getGenero());
        setPorte(pet.getPorte()+"");
        sociavel.setSelected(pet.isSociavel());
        castrado.setSelected(pet.isCastrado());
        is.openFoto(pet.getFoto(), is.imgDisplay);
        acessorios.setText(pet.getAcessorio());
        obs.setText(pet.getObs());
        donoId = pet.getDonoId();
        donoNome.setText(pet.getDonoNome());
        servicosPet.setVisible(petLoaded);
    }
    
    public void setPorte(String porte){
        if(porte.toLowerCase().indexOf("p") > -1){
            small.setSelected(true);
        } else {
            large.setSelected(true);
        }
    }
    

    
    
    public void setAlert(String msg){
        alert.setText(msg);
    }
    
    public String getPorte(String p){
        if(p.equals("p")){
            return "Pequeno";
        } else {
            return "Grande";
        }
    }
    
    @Override
    public boolean rules(){
        boolean matchRules;

        if(nome.getText().isEmpty()){
            matchRules = false;
            setAlert("Preencha um nome");
        } else {
            if(raca.getText().isEmpty()){
                matchRules = false;
                setAlert("Preencha uma raça");
            } else {
                if(nascimento.getText().isEmpty()){
                    matchRules = false;
                    setAlert("Preencha a data de nascimento");
                } else {
                    if(donoNome.getText().isEmpty()){
                        matchRules = false;
                        setAlert("Escolha um dono");
                    } else {
                        if(PetDB.isRegistred(nome.getText(), this.donoId) && !petLoaded){
                            matchRules = false;
                            setAlert("Já existe um pet com este nome para o mesmo dono");
                        } else {
                            matchRules = true;
                            setAlert("");
                        }
                    }
                }
            }
        }
        return matchRules;
    }

    
    public void setDono(int donoId, String donoNome){
        this.donoNome.setText(donoNome);
        this.donoId = donoId;
    }
       
    public void donoPicker(){
        ListarPessoa l = new ListarPessoa(PessoaDB.getAll("Cliente"));
    }
    
    public void newService(Pet pet){
        this.pet = pet;
        EscolherTipoServico s = new EscolherTipoServico(pet);
    }
    
    public Pet getPet(){
        return this.pet;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        genero = new javax.swing.ButtonGroup();
        porte = new javax.swing.ButtonGroup();
        nome = new javax.swing.JTextField();
        raca = new javax.swing.JTextField();
        nascimento = new javax.swing.JTextField();
        area = new javax.swing.JPanel();
        target = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        acessorios = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        castrado = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        sociavel = new javax.swing.JCheckBox();
        alert = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        portePanel = new javax.swing.JPanel();
        small = new javax.swing.JRadioButton();
        large = new javax.swing.JRadioButton();
        genderPanel = new javax.swing.JPanel();
        male = new javax.swing.JRadioButton();
        female = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        servicosPet = new javax.swing.JPanel();
        target2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        donoNome = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        panelServicos = new javax.swing.JPanel();
        tipoServico = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("2"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nome.setToolTipText("");
        nome.setBorder(null);
        add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 52, 240, 35));

        raca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        raca.setBorder(null);
        add(raca, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 117, 240, 35));

        nascimento.setEditable(false);
        nascimento.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        nascimento.setBorder(null);
        nascimento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nascimentoMouseClicked(evt);
            }
        });
        nascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nascimentoActionPerformed(evt);
            }
        });
        add(nascimento, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 187, 240, 35));

        area.setBackground(new java.awt.Color(255, 255, 255));
        area.setBorder(javax.swing.BorderFactory.createTitledBorder("Foto"));
        area.setMaximumSize(new java.awt.Dimension(200, 300));
        area.setMinimumSize(new java.awt.Dimension(200, 300));
        area.setPreferredSize(new java.awt.Dimension(200, 300));

        javax.swing.GroupLayout targetLayout = new javax.swing.GroupLayout(target);
        target.setLayout(targetLayout);
        targetLayout.setHorizontalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        targetLayout.setVerticalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(target, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaLayout.createSequentialGroup()
                .addComponent(target, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );

        add(area, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, 250, 232));

        jScrollPane1.setBorder(null);

        acessorios.setBorder(null);
        jScrollPane1.setViewportView(acessorios);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 306, 250, 70));

        jScrollPane2.setBorder(null);
        jScrollPane2.setViewportView(obs);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 250, 70));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setMaximumSize(new java.awt.Dimension(250, 69));
        jPanel3.setMinimumSize(new java.awt.Dimension(250, 69));
        jPanel3.setPreferredSize(new java.awt.Dimension(250, 69));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(15, 59, 146));
        jLabel7.setText("Castração");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 5, 80, -1));

        castrado.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        castrado.setForeground(new java.awt.Color(15, 59, 146));
        castrado.setText("Castrado");
        castrado.setAlignmentY(0.0F);
        castrado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        castrado.setBorderPainted(true);
        castrado.setContentAreaFilled(false);
        castrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        castrado.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel3.add(castrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 25, 116, 32));

        jLabel11.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(15, 59, 146));
        jLabel11.setText("Sociabilidade");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, -1, -1));

        sociavel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        sociavel.setForeground(new java.awt.Color(15, 59, 146));
        sociavel.setText("Sociável");
        sociavel.setAlignmentY(0.0F);
        sociavel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        sociavel.setBorderPainted(true);
        sociavel.setContentAreaFilled(false);
        sociavel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sociavel.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel3.add(sociavel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 25, 109, 32));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 350, 250, -1));

        alert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(204, 51, 0));
        alert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 470, 26));

        jLabel30.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(15, 59, 146));
        jLabel30.setText("Nascimento");
        add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 165, -1, 20));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        portePanel.setBackground(new java.awt.Color(255, 255, 255));
        portePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        portePanel.setMaximumSize(new java.awt.Dimension(250, 69));
        portePanel.setMinimumSize(new java.awt.Dimension(250, 69));
        portePanel.setPreferredSize(new java.awt.Dimension(250, 69));

        porte.add(small);
        small.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        small.setSelected(true);
        small.setText("Pequeno");
        small.setActionCommand("p");
        small.setContentAreaFilled(false);
        small.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                smallItemStateChanged(evt);
            }
        });

        porte.add(large);
        large.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        large.setText("Grande");
        large.setActionCommand("g");
        large.setContentAreaFilled(false);
        large.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                largeItemStateChanged(evt);
            }
        });
        large.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                largeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout portePanelLayout = new javax.swing.GroupLayout(portePanel);
        portePanel.setLayout(portePanelLayout);
        portePanelLayout.setHorizontalGroup(
            portePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portePanelLayout.createSequentialGroup()
                .addGroup(portePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(small)
                    .addComponent(large))
                .addGap(26, 151, Short.MAX_VALUE))
        );
        portePanelLayout.setVerticalGroup(
            portePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(portePanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(small)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(large)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel4.add(portePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 116, 80));

        genderPanel.setBackground(new java.awt.Color(255, 255, 255));
        genderPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        genderPanel.setMaximumSize(new java.awt.Dimension(250, 69));
        genderPanel.setMinimumSize(new java.awt.Dimension(250, 69));
        genderPanel.setPreferredSize(new java.awt.Dimension(250, 69));

        genero.add(male);
        male.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        male.setSelected(true);
        male.setText("Macho");
        male.setActionCommand("m");
        male.setContentAreaFilled(false);
        male.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                maleItemStateChanged(evt);
            }
        });

        genero.add(female);
        female.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        female.setText("Femea");
        female.setActionCommand("f");
        female.setContentAreaFilled(false);
        female.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                femaleItemStateChanged(evt);
            }
        });
        female.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                femaleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout genderPanelLayout = new javax.swing.GroupLayout(genderPanel);
        genderPanel.setLayout(genderPanelLayout);
        genderPanelLayout.setHorizontalGroup(
            genderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(genderPanelLayout.createSequentialGroup()
                .addGroup(genderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(male)
                    .addComponent(female))
                .addGap(31, 167, Short.MAX_VALUE))
        );
        genderPanelLayout.setVerticalGroup(
            genderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, genderPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(male)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(female)
                .addContainerGap())
        );

        jPanel4.add(genderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 108, 80));

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 59, 146));
        jLabel9.setText("Porte");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 70, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(15, 59, 146));
        jLabel14.setText("Sexo");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 250, -1));

        servicosPet.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout target2Layout = new javax.swing.GroupLayout(target2);
        target2.setLayout(target2Layout);
        target2Layout.setHorizontalGroup(
            target2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 487, Short.MAX_VALUE)
        );
        target2Layout.setVerticalGroup(
            target2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout servicosPetLayout = new javax.swing.GroupLayout(servicosPet);
        servicosPet.setLayout(servicosPetLayout);
        servicosPetLayout.setHorizontalGroup(
            servicosPetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicosPetLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(target2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );
        servicosPetLayout.setVerticalGroup(
            servicosPetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(servicosPetLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(target2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(servicosPet, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 70, 540, 410));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, 40));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 50, 170, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, 140, 40));

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 115, 170, 40));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 185, 140, 40));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 185, 170, 40));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel13.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 405, 130, 10));

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel15.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 405, 170, 10));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 415, 130, 30));

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 415, 170, 30));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 445, 130, 30));

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 445, 170, 30));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel20.setToolTipText("");
        jLabel20.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 455, 130, 30));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel21.setToolTipText("");
        jLabel21.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 455, 170, 30));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel22.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 302, 130, 20));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel23.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 302, 170, 20));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 130, 30));

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 310, 170, 30));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 130, 30));

        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 340, 170, 30));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel28.setToolTipText("");
        jLabel28.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 350, 130, 30));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel29.setToolTipText("");
        jLabel29.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 350, 170, 30));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(15, 59, 146));
        jLabel8.setText("Observação");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 385, -1, -1));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        donoNome.setEditable(false);
        donoNome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        donoNome.setBorder(null);
        donoNome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                donoNomeMouseClicked(evt);
            }
        });
        donoNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                donoNomeActionPerformed(evt);
            }
        });
        jPanel1.add(donoNome, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 2, 240, 35));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 40));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 170, 40));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 445, 260, 50));

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(15, 59, 146));
        jLabel31.setText("Dono");
        add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 425, -1, -1));

        jLabel32.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(15, 59, 146));
        jLabel32.setText("Acessórios");
        add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        panelServicos.setOpaque(false);

        tipoServico.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Serviços", "Hospedagens" }));
        tipoServico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoServicoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout panelServicosLayout = new javax.swing.GroupLayout(panelServicos);
        panelServicos.setLayout(panelServicosLayout);
        panelServicosLayout.setHorizontalGroup(
            panelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServicosLayout.createSequentialGroup()
                .addComponent(tipoServico, 0, 148, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelServicosLayout.setVerticalGroup(
            panelServicosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServicosLayout.createSequentialGroup()
                .addComponent(tipoServico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        add(panelServicos, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 20, 160, 50));

        jLabel33.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(15, 59, 146));
        jLabel33.setText("Raça");
        add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 95, -1, -1));

        jLabel34.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(15, 59, 146));
        jLabel34.setText("Nome");
        add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, -1, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void femaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_femaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_femaleActionPerformed

    private void largeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_largeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_largeActionPerformed

    private void nascimentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nascimentoMouseClicked
        dataPicker();
    }//GEN-LAST:event_nascimentoMouseClicked

    private void donoNomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoNomeMouseClicked
        donoPicker();
    }//GEN-LAST:event_donoNomeMouseClicked

    private void nascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nascimentoActionPerformed

    private void maleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_maleItemStateChanged
        updateCheckbox();
    }//GEN-LAST:event_maleItemStateChanged

    private void smallItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_smallItemStateChanged
        updateCheckbox();
    }//GEN-LAST:event_smallItemStateChanged

    private void femaleItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_femaleItemStateChanged
        updateCheckbox();
    }//GEN-LAST:event_femaleItemStateChanged

    private void largeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_largeItemStateChanged
        updateCheckbox();
    }//GEN-LAST:event_largeItemStateChanged

    private void donoNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_donoNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_donoNomeActionPerformed

    private void tipoServicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoServicoItemStateChanged
        setPanelServico(pet);
    }//GEN-LAST:event_tipoServicoItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextPane acessorios;
    private javax.swing.JLabel alert;
    private javax.swing.JPanel area;
    private javax.swing.JCheckBox castrado;
    private javax.swing.JTextField donoNome;
    private javax.swing.JRadioButton female;
    private javax.swing.JPanel genderPanel;
    private javax.swing.ButtonGroup genero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton large;
    private javax.swing.JRadioButton male;
    private javax.swing.JTextField nascimento;
    private javax.swing.JTextField nome;
    private javax.swing.JTextPane obs;
    private javax.swing.JPanel panelServicos;
    private javax.swing.ButtonGroup porte;
    private javax.swing.JPanel portePanel;
    private javax.swing.JTextField raca;
    private javax.swing.JPanel servicosPet;
    private javax.swing.JRadioButton small;
    private javax.swing.JCheckBox sociavel;
    private javax.swing.JPanel target;
    private javax.swing.JPanel target2;
    private javax.swing.JComboBox<String> tipoServico;
    // End of variables declaration//GEN-END:variables
}
