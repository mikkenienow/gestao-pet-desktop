package gestaopet.view.New.financeiro;

import gestaopet.DB.FinanceiroDB;
import gestaopet.V;
import gestaopet.classes.DateTools;
import gestaopet.classes.Financeiro;
import gestaopet.classes.FunctionButton;
import gestaopet.classes.GlobalPanel;
import gestaopet.components.DataPicker;
import gestaopet.enums.DateFormat;
import gestaopet.enums.DateMethods;
import gestaopet.tema.ComboBox.ComboBox;
import gestaopet.tema.scroll.SpecialScroll;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListarFinanceiro extends GlobalPanel {
    String param;
    private List<Financeiro> financeiroList = new ArrayList<>();
    public PendencyModal np;
    private SpecialScroll ss;
    ComboBox cb1;
    DataPicker dpi;
    DataPicker dpf;
    Date di;
    Date df ;
    ResumeModal rm;
    boolean ocultos = false;
    
    public ListarFinanceiro(int param) {
        initComponents();
        this.param = param + "";
        
        ss = new SpecialScroll(finPanel);
        scrollArea.add(ss);
        ss.resetPosition();
        ComboBox.setTheme(cb1, choice1, new int[] {783,13}, 40,0, this);
        
        FunctionButton[] ini = {
            new FunctionButton("Saida", true,"lancar1.png", this.param),
            new FunctionButton("Ocultos", true,"ocultosP0.png", this.param),
            new FunctionButton("Voltar", true,"icons8_left_2_35px.png", this.param),
        };
        buttonsInit(ini);
        buttonList.setBackground(Color.WHITE);        
        Date today = DateTools.getDate(DateMethods.TODAY, 0, 0, 0);
        int y = DateTools.formatDate(today, DateFormat.YEAR);
        int m = DateTools.formatDate(today, DateFormat.MONTH);
        int d = DateTools.formatDate(today, DateFormat.DAY);
        
        di = DateTools.getDate(DateMethods.FIRSTDAYOFMONTH, y, m, d);
        df = DateTools.getDate(DateMethods.LASTDAYOFMONTH, y, m, d);
        
        dIni.setText(DateTools.dateToString(di));
        dFin.setText(DateTools.dateToString(df));
                
        financeiroList = null;
        loadInfo1();
    }
    
    @Override
    public void action(ActionEvent evt){
        switch(evt.getActionCommand()){
            case "b1" :     //salvar
                newLancamento();
                break;      //excluir
            case "b2" :
                ocultosView();
                break;
            case "b3" :     //voltar
                 V.nav.back();
                break;
        }
    }
    
    public void newLancamento(){
        SaidaUnicaModal sum = new SaidaUnicaModal();
    }
    
    public void ocultosView(){
        FunctionButton[] ini;
        if(!ocultos){
            ocultos = true;
            ini = new FunctionButton[]{
            new FunctionButton("Saida", true,"lancar1.png", this.param),
            new FunctionButton("Ocultos", true,"lancadosP0.png", this.param),
            new FunctionButton("Voltar", true,"icons8_left_2_35px.png", this.param)};
        } else {
            ocultos = false;
            ini = new FunctionButton[] {
            new FunctionButton("Saida", true,"lancar1.png", this.param),
            new FunctionButton("Ocultos", true,"ocultosP0.png", this.param),
            new FunctionButton("Voltar", true,"icons8_left_2_35px.png", this.param)};
        }
        updateButtons(ini);
        loadList();
    }
    

    
    public String[] getDate(){
        String[] output = {dIni.getText(), dFin.getText()};
        return output;
    }
    
    public List<Financeiro> getFinList(){
        return this.financeiroList;
    }
    
    public void loadList() {
        int y = 0;
        financeiroList = FinanceiroDB.getAll(choice1.getSelectedItem().toString(), dIni.getText(), dFin.getText(), ocultos);
        
        area.removeAll();
        area.setPreferredSize(new Dimension(950, 42 * financeiroList.size()));
        area.setSize(new Dimension(950, 42 * financeiroList.size()));
        area.setBackground(Color.white);
        
        for (int i = 0; i < financeiroList.size(); i++) {
            FinanceiroItemList estoqueItem = new FinanceiroItemList(financeiroList.get(i));
            area.add(estoqueItem);
            
            estoqueItem.setLocation(0, y);
            estoqueItem.setPreferredSize(new Dimension(950, 42));
            estoqueItem.setSize(new Dimension(950, 42));
            
            y = y + 42;
            
        }
        loadInfo1();
        ss.updateSize();
        ss.resetPosition();
        area.repaint();
        area.revalidate();
    }
    
    public void loadInfo1(){
        String count1 = FinanceiroDB.loadInfo1() + "";
        jLabel1.setText(count1);
    }
    
     public void openDialog() {
         
         try {
             np.show(true);
         } catch (Exception e) {
             np = new PendencyModal();
         }
    }
    public void resumeView(){
        try {
            rm.show(true);
        } catch (Exception e) {
            rm = new ResumeModal();
        }
        rm.loadResume();
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        scrollArea = new javax.swing.JPanel();
        finPanel = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        buttonList = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        dataLabel = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        valorLabel = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        taxaLabel = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        porteLabel = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        donoLabel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nomeLabel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        dIni = new javax.swing.JTextField();
        dFin = new javax.swing.JTextField();
        choice1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setBackground(new java.awt.Color(255, 255, 255));
        setName("4"); // NOI18N

        scrollArea.setOpaque(false);

        javax.swing.GroupLayout scrollAreaLayout = new javax.swing.GroupLayout(scrollArea);
        scrollArea.setLayout(scrollAreaLayout);
        scrollAreaLayout.setHorizontalGroup(
            scrollAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 69, Short.MAX_VALUE)
        );
        scrollAreaLayout.setVerticalGroup(
            scrollAreaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 430, Short.MAX_VALUE)
        );

        finPanel.setBorder(null);
        finPanel.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        finPanel.setPreferredSize(new java.awt.Dimension(668, 200));
        finPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                finPanelMouseWheelMoved(evt);
            }
        });

        area.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 954, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 415, Short.MAX_VALUE)
        );

        finPanel.setViewportView(area);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("qntd");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 102, 204));
        jLabel2.setText("Lançamentos pendentes");

        buttonList.setForeground(new java.awt.Color(0, 51, 153));
        buttonList.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/view0.png"))); // NOI18N
        buttonList.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 153), 1, true));
        buttonList.setBorderPainted(false);
        buttonList.setContentAreaFilled(false);
        buttonList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonListActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dataLabel.setBackground(new java.awt.Color(255, 255, 255));
        dataLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                dataLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                dataLabelMouseExited(evt);
            }
        });
        dataLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel19.setText("jLabel1");
        dataLabel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel20.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(15, 59, 146));
        jLabel20.setText("Data de registro");
        dataLabel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel2.add(dataLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 170, 30));

        valorLabel.setBackground(new java.awt.Color(255, 255, 255));
        valorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                valorLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                valorLabelMouseExited(evt);
            }
        });
        valorLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel17.setText("jLabel1");
        valorLabel.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel18.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(15, 59, 146));
        jLabel18.setText("Ajustado");
        valorLabel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel2.add(valorLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 90, 30));

        taxaLabel.setBackground(new java.awt.Color(255, 255, 255));
        taxaLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                taxaLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                taxaLabelMouseExited(evt);
            }
        });
        taxaLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel15.setText("jLabel1");
        taxaLabel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jLabel16.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(15, 59, 146));
        jLabel16.setText("Ajuste");
        taxaLabel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel2.add(taxaLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 90, 30));

        porteLabel.setBackground(new java.awt.Color(255, 255, 255));
        porteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                porteLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                porteLabelMouseExited(evt);
            }
        });
        porteLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(15, 59, 146));
        jLabel13.setText("Valor");
        porteLabel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel14.setText("jLabel1");
        porteLabel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jPanel2.add(porteLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 0, 90, 30));

        donoLabel.setBackground(new java.awt.Color(255, 255, 255));
        donoLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                donoLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                donoLabelMouseExited(evt);
            }
        });
        donoLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(15, 59, 146));
        jLabel9.setText("Descrição");
        donoLabel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel10.setText("jLabel1");
        donoLabel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 2, -1));

        jPanel2.add(donoLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 0, 200, 30));

        nomeLabel.setBackground(new java.awt.Color(255, 255, 255));
        nomeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nomeLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nomeLabelMouseExited(evt);
            }
        });
        nomeLabel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/icons/angryimg.png"))); // NOI18N
        jLabel11.setText("jLabel1");
        nomeLabel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 2, -1));

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(15, 59, 146));
        jLabel12.setText("Categoria");
        nomeLabel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 5, -1, 25));

        jPanel2.add(nomeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 30));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dIni.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dIni.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dIni.setBorder(null);
        dIni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dIniMouseClicked(evt);
            }
        });
        dIni.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dIniPropertyChange(evt);
            }
        });
        jPanel1.add(dIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 2, 146, 35));

        dFin.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        dFin.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dFin.setBorder(null);
        dFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dFinMouseClicked(evt);
            }
        });
        dFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dFinPropertyChange(evt);
            }
        });
        jPanel1.add(dFin, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 2, 146, 35));

        choice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Pagos", "Pendentes" }));
        choice1.setPreferredSize(new java.awt.Dimension(56, 40));
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });
        jPanel1.add(choice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(836, 0, 98, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 100, -1));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 0, 95, -1));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, 95, -1));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/resumo0.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 100, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(finPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 954, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 0, 0)
                            .addComponent(buttonList, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(75, 75, 75)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 963, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(405, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonList, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(finPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scrollArea, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void donoLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoLabelMouseEntered
        donoLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_donoLabelMouseEntered

    private void donoLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_donoLabelMouseExited
        donoLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_donoLabelMouseExited

    private void nomeLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeLabelMouseEntered
        nomeLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_nomeLabelMouseEntered

    private void nomeLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nomeLabelMouseExited
        nomeLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_nomeLabelMouseExited

    private void porteLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_porteLabelMouseEntered
        porteLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_porteLabelMouseEntered

    private void porteLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_porteLabelMouseExited
        porteLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_porteLabelMouseExited

    private void taxaLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxaLabelMouseEntered
        taxaLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_taxaLabelMouseEntered

    private void taxaLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taxaLabelMouseExited
        taxaLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_taxaLabelMouseExited

    private void valorLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valorLabelMouseEntered
        valorLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_valorLabelMouseEntered

    private void valorLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_valorLabelMouseExited
        valorLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_valorLabelMouseExited

    private void dataLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataLabelMouseEntered
        dataLabel.setBackground(new Color(204,204,255));
    }//GEN-LAST:event_dataLabelMouseEntered

    private void dataLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dataLabelMouseExited
        dataLabel.setBackground(Color.WHITE);
    }//GEN-LAST:event_dataLabelMouseExited

    private void finPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_finPanelMouseWheelMoved
        ss.scroolAction(finPanel.getVerticalScrollBar().getValue());
    }//GEN-LAST:event_finPanelMouseWheelMoved

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        financeiroList = null;
        loadList();
        loadInfo1();
    }//GEN-LAST:event_choice1ItemStateChanged

    private void buttonListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonListActionPerformed
        openDialog();
    }//GEN-LAST:event_buttonListActionPerformed

    private void dIniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dIniMouseClicked
        try {
            dpi.show(true);
            //dpi.setInput(DateTools.dateToString(di));
        } catch (Exception e) {
            dpi = new DataPicker(dIni);
            dpi.setInput(DateTools.dateToString(di));
        }
    }//GEN-LAST:event_dIniMouseClicked

    private void dFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dFinMouseClicked
        try {
            dpf.show(true);
            //dpf.setInput(DateTools.dateToString(df));
        } catch (Exception e) {
            dpf = new DataPicker(dFin);
            dpf.setInput(DateTools.dateToString(df));
        }
    }//GEN-LAST:event_dFinMouseClicked

    private void dIniPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dIniPropertyChange
        loadList();
    }//GEN-LAST:event_dIniPropertyChange

    private void dFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dFinPropertyChange
        loadList();
    }//GEN-LAST:event_dFinPropertyChange

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        resumeView();        
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area;
    private javax.swing.JButton buttonList;
    private javax.swing.JComboBox<String> choice1;
    private javax.swing.JTextField dFin;
    private javax.swing.JTextField dIni;
    private javax.swing.JPanel dataLabel;
    private javax.swing.JPanel donoLabel;
    private javax.swing.JScrollPane finPanel;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel nomeLabel;
    private javax.swing.JPanel porteLabel;
    private javax.swing.JPanel scrollArea;
    private javax.swing.JPanel taxaLabel;
    private javax.swing.JPanel valorLabel;
    // End of variables declaration//GEN-END:variables
}
