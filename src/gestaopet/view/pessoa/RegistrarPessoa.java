package gestaopet.view.pessoa;

import gestaopet.DB.PessoaDB;
import gestaopet.V;
import gestaopet.classes.FunctionButton;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.InputTools;
import gestaopet.classes.Pessoa;
import gestaopet.enums.PersonMode;
import static gestaopet.enums.PersonMode.CLIENTE;
import static gestaopet.enums.PersonMode.COLABORADOR;
import static gestaopet.enums.PersonMode.FORNECEDOR;
import java.awt.event.ActionEvent;
import java.util.List;


public class RegistrarPessoa extends GlobalPanel {
    private String param;
    private String tipo = "";
    private String[][] base;
    private boolean backToPet = false;
    private boolean pessoaLoaded = false;
    private boolean pessoaNew = false;
    private int pessoaId;
    private VisualizarSaldo vs;
    public RegistrarPessoa(int param) {
        initComponents();
        this.param = param + "";
        base = new String[][]{
            {"doc",""},
            {"nome1",""},
            {"nome2",""},
            {"email",""},
            {"tel1",""},
            {"tel2",""},
            {"tel3",""},
            {"logradouro",""},
            {"num",""},
            {"comp",""},
            {"cidade",""},
            {"obs",""}
        };
        
        FunctionButton[] ini = {
            new FunctionButton("Salvar", true,"salvarTB0.png", this.param),            
            new FunctionButton("", true, "saldo1.png", this.param),
            new FunctionButton("Excluir", false,"excluirTB0.png", this.param),
            new FunctionButton("Voltar", true,"icons8_left_2_35px.png", this.param),
        };

        buttonsInit(ini);
    }
    
    @Override
    public void action(ActionEvent evt){
        switch(evt.getActionCommand()){
            case "b1" :     //salvar
                savePerson();
                break;      //excluir
            case "b2" :     //salvar
                saldoView();
                break;      //excluir
            case "b3" :
                deleteConfirmation();
                break;
            case "b4" :     //voltar
                pessoaLoaded =false;
                 V.nav.back();
                break;
        }
    }
    
    public void saldoView(){
        try {
            vs.show(true);
        } catch (Exception e) {
            vs = new VisualizarSaldo(this.pessoaId);
        }
    }
    
    public void setAlert(String msg){
        alert.setText(msg);
    }
    /*
    @Override
    public void deleteConfirmation(){
        DeleteEntity de = new DeleteEntity("Deseja mesmo excluir? ", this);
    }*/
    
    @Override
    public void delete(){
        pessoaLoaded = false;
        PessoaDB.delete(pessoaId);
        V.pessoa.loadPersonList("", tipo);
        //V.nav.setView(6);
        cleanAll();
        V.navigate(3);
        
    }
    
    public void loadPessoa(Pessoa pessoa){
        pessoaNew = false;
        tipo = pessoa.getTipo();
        if(tipo.equals("Cliente") && pessoaNew == false){
            V.nav.buttonEnableUpdate(new boolean[] {true, true, true});
        } else {
            V.nav.buttonEnableUpdate(new boolean[] {true, false, true});
        }
        cleanAll(this);
        pessoaLoaded = true;
        pessoaId = pessoa.getId();
        nome1.setText(pessoa.getNome1());
        nome2.setText(pessoa.getNome2());
        doc.setText(pessoa.getDoc());
        try {
            setAdress(pessoa.getEndereco());
        } catch (Exception e) {
            //V.showMessage("Não foi possível carregar o endereço corretamente. Erro: " + e, "OK");
        }
        cidade.setText(pessoa.getCidade());
        email.setText(pessoa.getEmail());
        tel1.setText(pessoa.getTel1());
        tel2.setText(pessoa.getTel2());
        tel3.setText(pessoa.getTel3());
        obs.setText(pessoa.getObs());
        
    }
    
    
    public void cleanAll(){
        //cleanAll(this);
        nome1.setText("");
        nome2.setText("");
        doc.setText("");
        email.setText("");
        tel1.setText("");
        tel2.setText("");
        tel3.setText("");
        logradouro.setText("");
        num.setText("");
        cidade.setText("");
        comp.setText("");
        obs.setText("");
        uf.setSelectedIndex(23);
        pessoaId = 0;
    }
    
    @Override
    public void openDialog() {
        NovoRegistro np = new NovoRegistro();
    }
    
    public String getAddress(String log, String num, String comp){
        String div = "##";
        return log + div + num + div + comp;
    }
    
    public void setAdress(String input){
        String div = "##";
        String[] l = input.split(div);
        logradouro.setText(l[0]);
        num.setText(l[1]);
        comp.setText(l[2]);
    }
    
    public void savePerson(){
        Pessoa p = null;

        if(rules()){
            p = new Pessoa(
                    tipo,
                    doc.getText(),
                    nome1.getText(),
                    nome2.getText(),
                    email.getText(),
                    tel1.getText(),
                    tel2.getText(),
                    tel3.getText(),
                    getAddress(logradouro.getText(),num.getText(),comp.getText()),
                    cidade.getText(),
                    uf.getSelectedItem().toString(),
                    obs.getText()
            );

            if(pessoaLoaded){

                p.setId(pessoaId);
                PessoaDB.update(p);
                V.pessoa.loadPersonList("", tipo);
                V.navigate(3);
            } else {

                int id = PessoaDB.insert(p).getId();
                p.setId(id);

                if(this.backToPet){
                    this.backToPet = false;
                    V.nav.setView(2);
                    V.petReg.setDono(p.getId(), p.getNomeCompleto());
                }
            }
            if(pessoaNew){

                V.pessoa.loadPersonList("", tipo);
                V.navigate(3);
            }
            pessoaLoaded = false;
        }
    }
    
    public void newPersonFromPet(){
        this.backToPet = true;
        cleanAll();
        V.nav.setView(6);
    }
    
    @Override
    public boolean rules(){
        boolean matchRules = false;
        String n1 = this.tipo.equals("Fornecedor")? "Razão Social": "Nome";
        String n2 = this.tipo.equals("Fornecedor")? "Nome Fantasia": "Sobrenome";
        String d = this.tipo.equals("Fornecedor")? "CNPJ": "CPF";
        
        
        if(nome1.getText().isEmpty()){
            matchRules = false;
            setAlert("Preencha " + n1);
        } else {
            if(nome2.getText().isEmpty()){
                matchRules = false;
                setAlert("Preencha " + n2);
            } else {
                if(doc.getText().isEmpty()){
                    matchRules = false;
                    setAlert("Preencha " + d);
                } else {
                    if(PessoaDB.isRegistred(doc.getText()) && !pessoaLoaded){
                        matchRules = false;
                        setAlert("Já existe um cadastro com o documento informado!");          
                    } else {
                        if(tel1.getText().isEmpty()){
                            matchRules = false;
                            setAlert("Preencha Tel. 1");
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
    
    public void setMode(PersonMode mode){
        switch(mode){
            case CLIENTE :
                tipo = "Cliente";
                break;
            case FORNECEDOR :
                tipo = "Fornecedor";
                break;
            case COLABORADOR:
                tipo = "Colaborador";
                break;
        }
    }
    

    
    public String[][] sort(String[][] base){
        List<String[]> l = getContent(this);
        int total = l.size();
        for(String[] a: base){
            for(int j = 0; j < total; j++) {
                String[] s = getContent(this).get(j);
                if(s[0].equals(a[0])){
                    a[1] = s[1];
                }
            }
        }
        return base;
    }
    
    
    public void setRegistrationMode(PersonMode mode){
        cleanAll();
        setMode(mode);
        String n1 = this.tipo.equals("Fornecedor")? "Razão Social*": "Nome*";
        String n2 = this.tipo.equals("Fornecedor")? "Nome Fantasia*": "Sobrenome*";
        String d = this.tipo.equals("Fornecedor")? "CNPJ*": "CPF*";
        pessoaNew = true;
        nome.setText(n1);
        sobrenome.setText(n2);      
        documento.setText(d);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        alert = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        nome1 = new javax.swing.JTextField();
        nome2 = new javax.swing.JTextField();
        doc = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        sobrenome = new javax.swing.JLabel();
        nome = new javax.swing.JLabel();
        documento = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        logradouro = new javax.swing.JTextField();
        num = new javax.swing.JTextField();
        cidade = new javax.swing.JTextField();
        uf = new javax.swing.JComboBox<>();
        comp = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        nome7 = new javax.swing.JLabel();
        nome8 = new javax.swing.JLabel();
        nome9 = new javax.swing.JLabel();
        nome10 = new javax.swing.JLabel();
        nome11 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        email = new javax.swing.JTextField();
        tel1 = new javax.swing.JTextField();
        tel2 = new javax.swing.JTextField();
        tel3 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        nome3 = new javax.swing.JLabel();
        nome4 = new javax.swing.JLabel();
        nome5 = new javax.swing.JLabel();
        nome6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        obs = new javax.swing.JTextArea();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel52 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("6"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        alert.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        alert.setForeground(new java.awt.Color(204, 51, 0));
        alert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        alert.setName("alert"); // NOI18N
        add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 580, 30));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Dados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(15, 59, 146))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        nome1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        nome1.setText("Mikke");
        nome1.setToolTipText("");
        nome1.setBorder(null);
        nome1.setName("nome1"); // NOI18N
        jPanel1.add(nome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 42, 220, 35));

        nome2.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        nome2.setText("Nienow");
        nome2.setToolTipText("");
        nome2.setBorder(null);
        nome2.setName("nome2"); // NOI18N
        jPanel1.add(nome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 107, 220, 35));

        doc.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        doc.setText("ff");
        doc.setToolTipText("");
        doc.setBorder(null);
        doc.setName("doc"); // NOI18N
        doc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                docKeyTyped(evt);
            }
        });
        jPanel1.add(doc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 172, 220, 35));

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 150, 40));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 130, 40));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 105, 150, 40));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 105, 130, 40));

        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 150, 40));

        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 130, 40));

        sobrenome.setText("Sobrenome");
        jPanel1.add(sobrenome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 85, -1, 20));

        nome.setText("Nome");
        jPanel1.add(nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));

        documento.setText("Documento");
        jPanel1.add(documento, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, 20));

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 320, 280));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Endereço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(15, 59, 146))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logradouro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        logradouro.setText("quintono");
        logradouro.setToolTipText("");
        logradouro.setBorder(null);
        logradouro.setName("logradouro"); // NOI18N
        jPanel2.add(logradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 42, 400, 35));

        num.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        num.setText("456");
        num.setToolTipText("");
        num.setBorder(null);
        num.setName("num"); // NOI18N
        jPanel2.add(num, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 102, 150, 35));

        cidade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        cidade.setText("Timbó");
        cidade.setToolTipText("");
        cidade.setBorder(null);
        cidade.setName("cidade"); // NOI18N
        jPanel2.add(cidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 102, 90, 35));

        uf.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        uf.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));
        uf.setSelectedIndex(23);
        uf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146)));
        jPanel2.add(uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 120, 40));

        comp.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        comp.setToolTipText("");
        comp.setBorder(null);
        comp.setName("comp"); // NOI18N
        jPanel2.add(comp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 162, 400, 35));

        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 330, 40));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 130, 40));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 40));

        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 80, 40));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, 40));

        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 330, 40));

        jLabel42.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 100, 90, 40));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 80, 40));

        nome7.setText("Logradouro");
        jPanel2.add(nome7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));

        nome8.setText("Nº");
        jPanel2.add(nome8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        nome9.setText("Complemento");
        jPanel2.add(nome9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        nome10.setText("Cidade");
        jPanel2.add(nome10, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 50, 20));

        nome11.setText("UF");
        jPanel2.add(nome11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 50, 20));

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 10, 460, 280));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Contato", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(15, 59, 146))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        email.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        email.setText("ggg");
        email.setToolTipText("");
        email.setBorder(null);
        email.setName("email"); // NOI18N
        jPanel3.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 42, 220, 35));

        tel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tel1.setText("222");
        tel1.setToolTipText("");
        tel1.setBorder(null);
        tel1.setName("tel1"); // NOI18N
        tel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tel1ActionPerformed(evt);
            }
        });
        tel1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tel1KeyTyped(evt);
            }
        });
        jPanel3.add(tel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 102, 220, 35));

        tel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tel2.setToolTipText("");
        tel2.setBorder(null);
        tel2.setName("tel2"); // NOI18N
        tel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tel2KeyTyped(evt);
            }
        });
        jPanel3.add(tel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 162, 220, 35));

        tel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tel3.setToolTipText("");
        tel3.setBorder(null);
        tel3.setName("tel3"); // NOI18N
        tel3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tel3KeyTyped(evt);
            }
        });
        jPanel3.add(tel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 222, 220, 35));

        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 150, 40));

        jLabel29.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 130, 40));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 150, 40));

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 130, 40));

        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 160, 150, 40));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 130, 40));

        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 150, 40));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel3.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 130, 40));

        nome3.setText("E-mail");
        jPanel3.add(nome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 20));

        nome4.setText("Tel 1");
        jPanel3.add(nome4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, 20));

        nome5.setText("Tel 2");
        jPanel3.add(nome5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, 20));

        nome6.setText("Tel 3");
        jPanel3.add(nome6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, 20));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 320, 280));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)), "Observações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 11), new java.awt.Color(15, 59, 146))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBorder(null);

        obs.setColumns(20);
        obs.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        obs.setLineWrap(true);
        obs.setRows(5);
        obs.setText("dsjiosdjidsa");
        obs.setBorder(null);
        obs.setName("obs"); // NOI18N
        jScrollPane1.setViewportView(obs);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 26, 1070, 160));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel46.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 180, 30));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel47.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 180, 30));

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel50.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 20, 390, 20));

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jLabel51.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 390, 20));

        jPanel6.setOpaque(false);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel45.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, -10, 1090, 170));

        jPanel4.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 1110, 140));

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel52.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        jPanel5.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 30, 610, 170));

        jPanel4.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, -10, 540, -1));

        add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 1130, 200));
    }// </editor-fold>//GEN-END:initComponents

    private void tel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tel1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tel1ActionPerformed

    private void docKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_docKeyTyped
        doc.setText(InputTools.docFormat(doc.getText(), evt, evt.getKeyChar() + "", !tipo.equals("Fornecedor")));
    }//GEN-LAST:event_docKeyTyped

    private void tel1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tel1KeyTyped
        tel1.setText(InputTools.acceptInteger(tel1.getText(), evt, evt.getKeyChar() + ""));
    }//GEN-LAST:event_tel1KeyTyped

    private void tel2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tel2KeyTyped
        tel2.setText(InputTools.acceptInteger(tel2.getText(), evt, evt.getKeyChar() + ""));
    }//GEN-LAST:event_tel2KeyTyped

    private void tel3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tel3KeyTyped
        tel3.setText(InputTools.acceptInteger(tel3.getText(), evt, evt.getKeyChar() + ""));
    }//GEN-LAST:event_tel3KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private javax.swing.JTextField cidade;
    private javax.swing.JTextField comp;
    private javax.swing.JTextField doc;
    private javax.swing.JLabel documento;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField logradouro;
    private javax.swing.JLabel nome;
    private javax.swing.JTextField nome1;
    private javax.swing.JLabel nome10;
    private javax.swing.JLabel nome11;
    private javax.swing.JTextField nome2;
    private javax.swing.JLabel nome3;
    private javax.swing.JLabel nome4;
    private javax.swing.JLabel nome5;
    private javax.swing.JLabel nome6;
    private javax.swing.JLabel nome7;
    private javax.swing.JLabel nome8;
    private javax.swing.JLabel nome9;
    private javax.swing.JTextField num;
    private javax.swing.JTextArea obs;
    private javax.swing.JLabel sobrenome;
    private javax.swing.JTextField tel1;
    private javax.swing.JTextField tel2;
    private javax.swing.JTextField tel3;
    private javax.swing.JComboBox<String> uf;
    // End of variables declaration//GEN-END:variables
}
