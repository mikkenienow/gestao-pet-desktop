package gestaopet.view.New.estoque;

import gestaopet.DB.EstoqueDB;
import gestaopet.V;
import gestaopet.classes.Estoque;
import gestaopet.classes.FunctionButton;
import gestaopet.classes.GlobalPanel;
import gestaopet.tema.ComboBox.ComboBox;
import gestaopet.tema.scroll.SpecialScroll;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;

public class ListarEstoque extends GlobalPanel {
    
    private String param;
    private List<Estoque> estoqueList = new ArrayList<>();
    private ComboBox cb1;
    private ComboBox cb2;
    private SpecialScroll ss;
    
    public ListarEstoque(int param) {
        initComponents();
        ss = new SpecialScroll(estoqueScroll);
        scroll.add(ss);
        ss.resetPosition(); 
        
        this.param = param + "";
        ComboBox.setTheme(cb1, qntdChoice, new int[] {0,22}, 40,0, target);
        ComboBox.setTheme(cb2, valueChoice1, new int[] {0,97}, 40, 0,target);
        FunctionButton[] ini = {
            //new FunctionButton("Salvar", true,"icons8_Save_35px.png", this.param),
            //new FunctionButton("Excluir", false,"icons8_Delete_Trash_35px.png", this.param),
            new FunctionButton("", true, "novo1.png", this.param),
            new FunctionButton("Voltar", true, "icons8_left_2_35px.png", this.param),};
        buttonsInit(ini);
        
        //estoqueScroll.setHorizontalScrollBarPolicy(estoqueScroll.HORIZONTAL_SCROLLBAR_NEVER);
        loadList();
    }
    
    public void action(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "b1": 
                V.estoque.clearAll();
                V.estoque.setMode(false);
                V.estoque.loadSupliers();
                V.nav.setView(12);
//                Estoque estoqueObj = new Estoque("Teste", 56.0, 4, 'S', "Teste DB", DateTools.stringToDate("12/12/2022", "00:00") , DateTools.stringToDate("12/12/2022", "00:00"), "dinheiro");;
//                EstoqueDB.insert(estoqueObj);
                break;      //Voltar
            case "b2":
                V.nav.back();
                break;
        }
    }
    

    
    public void loadList() {
        int y = 0;
        estoqueList = EstoqueDB.getAll(parseInt(quantidadeInput.getText()), qntdChoice.getSelectedItem().toString(), parseInt(valueInput1.getText()), valueChoice1.getSelectedItem().toString(), categoriaInput1.getText(), produtoInput2.getText());
        
        area.removeAll();
        area.setBackground(Color.white);
        area.setPreferredSize(new Dimension(880, 42 * estoqueList.size()));
        area.setSize(new Dimension(880, estoqueList.size() * 42));
        for(int k = 0; k < 1; k++){
            for (int i = 0; i < estoqueList.size(); i++) {
            EstoqueItemList estoqueItem = new EstoqueItemList(estoqueList.get(i));
            area.add(estoqueItem);
            
            estoqueItem.setLocation(0, y);
            estoqueItem.setPreferredSize(new Dimension(878, 42));
            estoqueItem.setSize(new Dimension(878, 42));
            
            y = y + 42;
            
        }
        }
        
        ss.updateSize();
        ss.resetPosition();
        area.repaint();
        area.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        estoqueScroll = new javax.swing.JScrollPane();
        area = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        categoriaInput1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        produtoInput2 = new javax.swing.JTextField();
        valueInput1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        quantidadeInput = new javax.swing.JTextField();
        filterButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        target = new javax.swing.JPanel();
        qntdChoice = new javax.swing.JComboBox<>();
        valueChoice1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        scroll = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("11"); // NOI18N

        estoqueScroll.setBorder(null);
        estoqueScroll.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        estoqueScroll.setPreferredSize(new java.awt.Dimension(668, 200));
        estoqueScroll.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                estoqueScrollMouseWheelMoved(evt);
            }
        });

        area.setPreferredSize(new java.awt.Dimension(0, 0));

        javax.swing.GroupLayout areaLayout = new javax.swing.GroupLayout(area);
        area.setLayout(areaLayout);
        areaLayout.setHorizontalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 747, Short.MAX_VALUE)
        );
        areaLayout.setVerticalGroup(
            areaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        estoqueScroll.setViewportView(area);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        jPanel1.setForeground(new java.awt.Color(15, 59, 146));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setText("Categoria");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 140, -1, -1));

        categoriaInput1.setBorder(null);
        categoriaInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                categoriaInput1ActionPerformed(evt);
            }
        });
        jPanel1.add(categoriaInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 163, 220, 35));

        jLabel5.setText("Produto");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 61, -1, -1));

        produtoInput2.setBorder(null);
        produtoInput2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                produtoInput2ActionPerformed(evt);
            }
        });
        jPanel1.add(produtoInput2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 82, 220, 35));

        valueInput1.setText("0");
        valueInput1.setBorder(null);
        valueInput1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueInput1ActionPerformed(evt);
            }
        });
        jPanel1.add(valueInput1, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 323, 85, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(80, 176, 230));
        jLabel2.setText("Filtro");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 17, 102, -1));

        quantidadeInput.setText("0");
        quantidadeInput.setBorder(null);
        quantidadeInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantidadeInputActionPerformed(evt);
            }
        });
        jPanel1.add(quantidadeInput, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 246, 85, 30));

        filterButton.setBackground(new java.awt.Color(0, 102, 255));
        filterButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        filterButton.setForeground(new java.awt.Color(255, 255, 255));
        filterButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/filtrar0.png"))); // NOI18N
        filterButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 102, 204), 2, true));
        filterButton.setBorderPainted(false);
        filterButton.setContentAreaFilled(false);
        filterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterButtonActionPerformed(evt);
            }
        });
        jPanel1.add(filterButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 365, 220, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 80, 210, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 160, 210, 40));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 110, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 110, -1));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 242, 60, -1));

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 242, 40, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(147, 317, 60, -1));

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 317, 40, -1));

        target.setBackground(new java.awt.Color(255, 204, 204));
        target.setOpaque(false);

        qntdChoice.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maior que", "Menor que" }));

        valueChoice1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Maior que", "Menor que" }));

        jLabel1.setText("Quantidade");

        javax.swing.GroupLayout targetLayout = new javax.swing.GroupLayout(target);
        target.setLayout(targetLayout);
        targetLayout.setHorizontalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(targetLayout.createSequentialGroup()
                .addGroup(targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(qntdChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(valueChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        targetLayout.setVerticalGroup(
            targetLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, targetLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addComponent(qntdChoice, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(valueChoice1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(target, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 220, 110, 140));

        jLabel6.setText("Valor");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(17, 290, 220, -1));

        scroll.setMinimumSize(new java.awt.Dimension(0, 0));
        scroll.setOpaque(false);
        scroll.setPreferredSize(new java.awt.Dimension(50, 400));

        javax.swing.GroupLayout scrollLayout = new javax.swing.GroupLayout(scroll);
        scroll.setLayout(scrollLayout);
        scrollLayout.setHorizontalGroup(
            scrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        scrollLayout.setVerticalGroup(
            scrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 419, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(estoqueScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(estoqueScroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(166, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void quantidadeInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantidadeInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_quantidadeInputActionPerformed

    private void filterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterButtonActionPerformed
        loadList();
    }//GEN-LAST:event_filterButtonActionPerformed

    private void categoriaInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_categoriaInput1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_categoriaInput1ActionPerformed

    private void produtoInput2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_produtoInput2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_produtoInput2ActionPerformed

    private void valueInput1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueInput1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valueInput1ActionPerformed

    private void estoqueScrollMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_estoqueScrollMouseWheelMoved
        ss.scroolAction(estoqueScroll.getVerticalScrollBar().getValue());
    }//GEN-LAST:event_estoqueScrollMouseWheelMoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area;
    private javax.swing.JTextField categoriaInput1;
    private javax.swing.JScrollPane estoqueScroll;
    private javax.swing.JButton filterButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField produtoInput2;
    private javax.swing.JComboBox<String> qntdChoice;
    private javax.swing.JTextField quantidadeInput;
    private javax.swing.JPanel scroll;
    private javax.swing.JPanel target;
    private javax.swing.JComboBox<String> valueChoice1;
    private javax.swing.JTextField valueInput1;
    // End of variables declaration//GEN-END:variables
}
