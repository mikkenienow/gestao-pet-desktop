package gestaopet.view.home;
import gestaopet.view.agendamento.reserva.ListarCanil;
import gestaopet.view.agendamento.general.Calendar;
import gestaopet.view.servicos.PainelServico;
import gestaopet.DB.DBManager;
import gestaopet.V;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.InputTools;
import gestaopet.classes.Navegation;
import java.awt.Color;
import gestaopet.enums.CalendarMode;
import gestaopet.view.New.estoque.ListarEstoque;
import gestaopet.view.New.estoque.RegistrarItem;
import gestaopet.view.New.financeiro.ListarFinanceiro;
import gestaopet.view.pessoa.RegistrarPessoa;
import gestaopet.view.pessoa.ListarPessoa;
import gestaopet.view.pet.RegistrarPet;
import gestaopet.view.pet.ListarPet;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;

public class Home extends javax.swing.JFrame {
    public Navegation nav;
    public static GlobalPanel[] pList;
    public Object[][] botoesPrincipais;
    private boolean buttonAction = true;
    
    public Home() {
        initComponents();
        setIcon();
        pList = getPanels();
        DBManager.start();
        nav = new Navegation(this);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.WHITE);
        nav.setView(0);
        botoesPrincipais = new Object[][] { {0,homeButton}, {1,petsButton}, {3,pessoasButton}, {11,estoqueButton}, {13,financeiroButton}};
    }
    
    public static GlobalPanel[] getPanels(){
        GlobalPanel[] list = {
            new StartPage(0),                               //0
            new ListarPet(1),                          //1
            new RegistrarPet(2),                     //2
            new ListarPessoa(3),                            //3
            new ListarFinanceiro(4),                           //4
            new ListarCanil(),                       //5
            new RegistrarPessoa(6),                              //6
            new PainelServico(false),                              //7
            new Calendar(CalendarMode.SERVICES),    //8
            new Calendar(CalendarMode.BOOKING),    //9
            new StartPage(10),                               //10
            new ListarEstoque(11),//11
            new RegistrarItem(12),//12
            new ListarFinanceiro(13)};//13
        return list;
    }
    
    public GlobalPanel[] getPanelList(){
        return this.pList;
    }
    
    public void actionTrigger(ActionEvent evt){
        getPanelList()[V.nav.getPos()].action(evt);
    }
    
    public void buttonTrigger(int b){
        
        for(int i = 0; i < botoesPrincipais.length; i++){
            if((int)botoesPrincipais[i][0] == b){
                ((JButton)botoesPrincipais[i][1]).setForeground(new Color(80,176,230));
            } else {
                ((JButton)botoesPrincipais[i][1]).setForeground(new Color(15,59,146));
            }
        }
        buttonAction = false;
    }

    public void buttonAction(int b){
        switch(b){
            case 0:
                nav.setView(0);
                break;
            case 1:
                V.pets.onLoad("","");
                nav.setView(1);
                break;
            case 3:
                V.pessoa.openDialog();
                break;
            case 11:
                nav.setView(11);
                V.estoqueLoad.loadList();
                break;
            case 13:
                V.fin.loadList();
                nav.setView(13);
                break;
        }
    }
    
    public void setColor(MouseEvent evt, Object[] t, int i){
        JComponent j = (JButton)t[1];
        if(buttonAction){
            if(nav.getPos() != i){
                switch(evt.getID()){
                case 504:
                    j.setForeground(new Color(0,204,204)); // over
                    break;
                case 505:
                    j.setForeground(new Color(15,59,146)); // exited
                    break;
                case 501:
                    j.setForeground(new Color(0,204,204)); // released
                    break;
                case 502:
                    j.setForeground(new Color(0,204,204)); // clicked
                    break;
              }  
            }
            
        }
        buttonAction = true;
    }
    
    public JButton[] getButtons(){
        JButton[] list = new JButton[buttonsPanel.getComponentCount() + 1];
        for(int i = 0; i < list.length; i++){
            if(i < list.length - 1){
                list[i] = (JButton)buttonsPanel.getComponents()[i];
            } else {
                list[i] = back;
            }
        }
        return list;
    }
    

    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        formViewArea = new javax.swing.JPanel();
        target = new javax.swing.JPanel();
        buttonsPanel = new javax.swing.JPanel();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        petsButton = new javax.swing.JButton();
        pessoasButton = new javax.swing.JButton();
        estoqueButton = new javax.swing.JButton();
        financeiroButton = new javax.swing.JButton();
        homeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gestão Pet");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/tema/icons/Botao.png"))); // NOI18N
        back.setBorder(null);
        back.setBorderPainted(false);
        back.setContentAreaFilled(false);
        back.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        back.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        back.setIconTextGap(0);
        back.setMargin(new java.awt.Insets(0, 0, 0, 0));
        back.setName("voltar"); // NOI18N
        back.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        back.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 40, 40));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/tema/icons/OrnamentoBottonLeft.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 410, 210, 250));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/tema/icons/OrnamentoTopRight.png"))); // NOI18N
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 0, 160, 210));

        formViewArea.setBackground(new java.awt.Color(255, 255, 255));
        formViewArea.setName("0"); // NOI18N

        target.setName("0"); // NOI18N

        javax.swing.GroupLayout targetLayout = new javax.swing.GroupLayout(target);
        target.setLayout(targetLayout);
        targetLayout.setHorizontalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1170, Short.MAX_VALUE)
        );
        targetLayout.setVerticalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 517, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout formViewAreaLayout = new javax.swing.GroupLayout(formViewArea);
        formViewArea.setLayout(formViewAreaLayout);
        formViewAreaLayout.setHorizontalGroup(
            formViewAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(target, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        formViewAreaLayout.setVerticalGroup(
            formViewAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(formViewAreaLayout.createSequentialGroup()
                .addComponent(target, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(formViewArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 111, 1170, 520));

        buttonsPanel.setBackground(new java.awt.Color(255, 255, 255));
        buttonsPanel.setMaximumSize(new java.awt.Dimension(535, 72));
        buttonsPanel.setMinimumSize(new java.awt.Dimension(535, 72));
        buttonsPanel.setPreferredSize(new java.awt.Dimension(535, 72));

        b1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b1.setToolTipText("");
        b1.setBorderPainted(false);
        b1.setContentAreaFilled(false);
        b1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b1.setIconTextGap(0);
        b1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b2.setToolTipText("");
        b2.setBorderPainted(false);
        b2.setContentAreaFilled(false);
        b2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b2.setIconTextGap(0);
        b2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b3.setToolTipText("");
        b3.setBorderPainted(false);
        b3.setContentAreaFilled(false);
        b3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b3.setIconTextGap(0);
        b3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b4.setToolTipText("");
        b4.setBorderPainted(false);
        b4.setContentAreaFilled(false);
        b4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b4.setIconTextGap(0);
        b4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b5.setToolTipText("");
        b5.setBorderPainted(false);
        b5.setContentAreaFilled(false);
        b5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b5.setIconTextGap(0);
        b5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b6.setToolTipText("");
        b6.setBorderPainted(false);
        b6.setContentAreaFilled(false);
        b6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b6.setIconTextGap(0);
        b6.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b7.setToolTipText("");
        b7.setBorderPainted(false);
        b7.setContentAreaFilled(false);
        b7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b7.setIconTextGap(0);
        b7.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bichofeliz/icons/icons8_wait_35px.png"))); // NOI18N
        b8.setToolTipText("");
        b8.setBorderPainted(false);
        b8.setContentAreaFilled(false);
        b8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        b8.setIconTextGap(0);
        b8.setMargin(new java.awt.Insets(0, 0, 0, 0));
        b8.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        b8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(380, Short.MAX_VALUE))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(b8, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b7, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b4, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        getContentPane().add(buttonsPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 540, 50));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setFocusTraversalPolicyProvider(true);

        petsButton.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        petsButton.setForeground(new java.awt.Color(15, 59, 146));
        petsButton.setText("Pets");
        petsButton.setAlignmentY(0.0F);
        petsButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51)));
        petsButton.setBorderPainted(false);
        petsButton.setContentAreaFilled(false);
        petsButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        petsButton.setIconTextGap(0);
        petsButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        petsButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                petsButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                petsButtonMouseExited(evt);
            }
        });
        petsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                petsButtonActionPerformed(evt);
            }
        });

        pessoasButton.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        pessoasButton.setForeground(new java.awt.Color(15, 59, 146));
        pessoasButton.setText("Pessoas");
        pessoasButton.setAlignmentY(0.0F);
        pessoasButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51)));
        pessoasButton.setBorderPainted(false);
        pessoasButton.setContentAreaFilled(false);
        pessoasButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pessoasButton.setIconTextGap(0);
        pessoasButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pessoasButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pessoasButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pessoasButtonMouseExited(evt);
            }
        });
        pessoasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pessoasButtonActionPerformed(evt);
            }
        });

        estoqueButton.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        estoqueButton.setForeground(new java.awt.Color(15, 59, 146));
        estoqueButton.setText("Estoque");
        estoqueButton.setAlignmentY(0.0F);
        estoqueButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51)));
        estoqueButton.setBorderPainted(false);
        estoqueButton.setContentAreaFilled(false);
        estoqueButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        estoqueButton.setIconTextGap(0);
        estoqueButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        estoqueButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                estoqueButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                estoqueButtonMouseExited(evt);
            }
        });
        estoqueButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoqueButtonActionPerformed(evt);
            }
        });

        financeiroButton.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        financeiroButton.setForeground(new java.awt.Color(15, 59, 146));
        financeiroButton.setText("Financeiro");
        financeiroButton.setAlignmentY(0.0F);
        financeiroButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51)));
        financeiroButton.setBorderPainted(false);
        financeiroButton.setContentAreaFilled(false);
        financeiroButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        financeiroButton.setIconTextGap(0);
        financeiroButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        financeiroButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                financeiroButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                financeiroButtonMouseExited(evt);
            }
        });
        financeiroButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                financeiroButtonActionPerformed(evt);
            }
        });

        homeButton.setFont(new java.awt.Font("Trebuchet MS", 1, 25)); // NOI18N
        homeButton.setForeground(new java.awt.Color(80, 176, 230));
        homeButton.setText("Home");
        homeButton.setAlignmentY(0.0F);
        homeButton.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 0, 51)));
        homeButton.setBorderPainted(false);
        homeButton.setContentAreaFilled(false);
        homeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        homeButton.setIconTextGap(0);
        homeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        homeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                homeButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                homeButtonMouseExited(evt);
            }
        });
        homeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(homeButton)
                .addGap(20, 20, 20)
                .addComponent(petsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pessoasButton)
                .addGap(20, 20, 20)
                .addComponent(estoqueButton)
                .addGap(20, 20, 20)
                .addComponent(financeiroButton)
                .addGap(69, 69, 69))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(homeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(petsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pessoasButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(estoqueButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(financeiroButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 540, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pessoasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pessoasButtonActionPerformed
        buttonAction(3);
    }//GEN-LAST:event_pessoasButtonActionPerformed

    private void petsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_petsButtonActionPerformed
        buttonAction(1);
    }//GEN-LAST:event_petsButtonActionPerformed

    private void financeiroButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_financeiroButtonActionPerformed
        buttonAction(13);
    }//GEN-LAST:event_financeiroButtonActionPerformed

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_b8ActionPerformed

    private void estoqueButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoqueButtonActionPerformed
        buttonAction(11);   
    }//GEN-LAST:event_estoqueButtonActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        actionTrigger(evt);
    }//GEN-LAST:event_backActionPerformed

    private void petsButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_petsButtonMouseEntered
        setColor(evt, botoesPrincipais[1],1);
    }//GEN-LAST:event_petsButtonMouseEntered

    private void petsButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_petsButtonMouseExited
        setColor(evt, botoesPrincipais[1],1);
    }//GEN-LAST:event_petsButtonMouseExited

    private void pessoasButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pessoasButtonMouseEntered
        setColor(evt, botoesPrincipais[2],3);
    }//GEN-LAST:event_pessoasButtonMouseEntered

    private void pessoasButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pessoasButtonMouseExited
        setColor(evt, botoesPrincipais[2],3);
    }//GEN-LAST:event_pessoasButtonMouseExited

    private void estoqueButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estoqueButtonMouseEntered
        setColor(evt, botoesPrincipais[3],11);
    }//GEN-LAST:event_estoqueButtonMouseEntered

    private void estoqueButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_estoqueButtonMouseExited
        setColor(evt, botoesPrincipais[3],11);
    }//GEN-LAST:event_estoqueButtonMouseExited

    private void financeiroButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_financeiroButtonMouseEntered
        setColor(evt, botoesPrincipais[4],13);
    }//GEN-LAST:event_financeiroButtonMouseEntered

    private void financeiroButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_financeiroButtonMouseExited
        setColor(evt, botoesPrincipais[4],13);
    }//GEN-LAST:event_financeiroButtonMouseExited

    private void homeButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseEntered
        setColor(evt, botoesPrincipais[0],0);
    }//GEN-LAST:event_homeButtonMouseEntered

    private void homeButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeButtonMouseExited
        setColor(evt, botoesPrincipais[0],0);
    }//GEN-LAST:event_homeButtonMouseExited

    private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeButtonActionPerformed
        buttonAction(0);
    }//GEN-LAST:event_homeButtonActionPerformed

    /**
     * @param args the  line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        try {

            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>*/
        //</editor-fold>
        //</editor-fold>*/
        //</editor-fold>
        //</editor-fold>*/
        //</editor-fold>
        //</editor-fold>*/

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton back;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JButton estoqueButton;
    private javax.swing.JButton financeiroButton;
    public javax.swing.JPanel formViewArea;
    private javax.swing.JButton homeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton pessoasButton;
    private javax.swing.JButton petsButton;
    public javax.swing.JPanel target;
    // End of variables declaration//GEN-END:variables

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/gestaopet/tema/icons/icon.png")));
    }
}
