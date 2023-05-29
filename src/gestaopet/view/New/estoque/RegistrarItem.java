package gestaopet.view.New.estoque;

import gestaopet.DB.EstoqueDB;
import gestaopet.DB.FinanceiroDB;
import gestaopet.DB.PessoaDB;
import gestaopet.V;
import gestaopet.classes.DateTools;
import gestaopet.classes.Estoque;
import gestaopet.classes.FinanceiroItem;
import gestaopet.classes.FunctionButton;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.InputTools;
import gestaopet.classes.Pessoa;
import gestaopet.components.DataPicker;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class RegistrarItem extends GlobalPanel {

    private String param;
    private DataPicker dataPicker;
    private DataPicker dataPicker1;
    private Estoque estoqueItem;
    private Boolean editMode;
    private List<Pessoa> supplierList = new ArrayList<>();

    public RegistrarItem(int param) {
        initComponents();
        alert.setVisible(false);
        this.param = param + "";
        FunctionButton[] ini = {
            new FunctionButton("Salvar", true, "salvarTB0.png", this.param),
            new FunctionButton("Voltar", true, "icons8_left_2_35px.png", this.param),};
        buttonsInit(ini);
        addOptions();
    }

    @Override
    public void action(ActionEvent evt) {
        switch (evt.getActionCommand()) {
            case "b1":     //salvar
                addItem();
                break;      //excluir
            case "b2":     //voltar
                V.nav.back();
                break;
        }
    }

    @Override
    public void openDialog() {
        InsertModal np = new InsertModal(this);
    }

//    @Override;
//    public void openDialog() {
//        EditModal np = new EditModal();
//    }
    public void addOptions() {
        choice1.add("Sim");
        choice1.add("Não");

        loadSupliers();

    }
    
    public void loadSupliers(){
        supplierList = PessoaDB.getAll("Fornecedor");
        choice3.removeAll();
        choice3.add("0 - Não informado");
        try {
            for (int i = 0; i < supplierList.size(); i++) {

                choice3.add(supplierList.get(i).getId() + " - " + supplierList.get(i).getNomeCompleto());
            }
        } catch (Exception e) {
            System.out.println(e + "Erro ao consultar fornecedores");
        }
    }

    public void addItem() {
        alert.setVisible(false);
        String[] idFornecedor = choice3.getSelectedItem().split(" -");
        int situ = 0;
        try {
            if ((textField1.getText() != null) && (textField2.getText() != null)
                    && (valor.getText() != null || valor.getText().equals("0,00")) && (textField4.getText() != null)
                    && (textField5.getText() != null) && (datePicker1.getText() != null)
                    && (datePicker2.getText() != null)) {

                if (editMode == true) {
                    Estoque estoque = new Estoque(textField1.getText(),
                            Double.parseDouble(valor.getText().replace(",", ".")),
                            Integer.parseInt(textField4.getText()),
                            choice1.getSelectedItem() + "",
                            textField2.getText(),
                            DateTools.stringToDate(datePicker1.getText(), "00:00"),
                            DateTools.stringToDate(datePicker2.getText(), "00:00"),
                            textField5.getText(),
                            situ,
                            Integer.parseInt(idFornecedor[0]));

                    EstoqueDB.update(estoque, estoqueItem.getIdEstoque());
                    editMode = false;
                    V.nav.setView(11);

                } else {
                    
                    
                    Estoque estoque = new Estoque(textField1.getText(),
                            Double.parseDouble(valor.getText().replace(",", ".")) * Integer.parseInt(textField4.getText()),
                            Integer.parseInt(textField4.getText()),
                            choice1.getSelectedItem() + "",
                            textField2.getText(),
                            DateTools.stringToDate(datePicker1.getText(), "00:00"),
                            DateTools.stringToDate(datePicker2.getText(), "00:00"),
                            textField5.getText(), 
                            situ,
                            Integer.parseInt(idFornecedor[0]));
                    int id = EstoqueDB.insert(estoque);
                    
                    if(situ == 1){
                        FinanceiroDB.addFInanceiroItem(new FinanceiroItem(id, "Estoque", estoque.getProduto(),
                                estoque.getValor(), estoque.getIdFornecedor() + "", estoque.getDataEntrada(), estoque.getQuantidade(), situ));
                    }
                    openDialog();
                }

            }
        } catch (Exception e) {
            alert.setVisible(true);
            System.out.println("Erro " + e);
        }
    }

    public void clearAll() {
        editMode = false;
        textField1.setText("");
        textField2.setText("");
        valor.setText("0,00");
        textField4.setText("");
        textField5.setText("");
        datePicker1.setText("");
        datePicker2.setText("");
        alert.setVisible(false);
    }

    public void updateInput(Estoque estoque) {
        estoqueItem = estoque;
        textField1.setText(estoqueItem.getProduto());
        textField2.setText(estoqueItem.getCategoria());
        valor.setText(InputTools.getStringValor(estoqueItem.getValor()));
        textField4.setText(estoqueItem.getQuantidade() + "");
        textField5.setText(estoqueItem.getFormaPagamento());
        datePicker1.setText(DateTools.dateToString(estoqueItem.getDataEntrada()));
        datePicker2.setText(DateTools.dateToString(estoqueItem.getDataVencimento()));
        choice1.select(estoqueItem.getFaturavel());
    }

    public void setMode(Boolean mode) {
        editMode = mode;
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textField1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textField4 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        valor = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textField5 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        datePicker1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        datePicker2 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        choice3 = new java.awt.Choice();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
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
        alert = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("6"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setText("Novo item de estoque");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 270, 40));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel2.setText("Produto");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 70, 20));

        textField1.setBorder(null);
        textField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField1ActionPerformed(evt);
            }
        });
        add(textField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 102, 230, 35));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel8.setText("Categoria");
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 70, 20));

        textField2.setBorder(null);
        add(textField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 172, 230, 35));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel9.setText("Quantidade");
        add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 290, 100, 20));

        textField4.setBorder(null);
        textField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textField4KeyTyped(evt);
            }
        });
        add(textField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 312, 230, 35));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel10.setText("Valor unitário");
        add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 100, 20));

        valor.setText("0,00");
        valor.setBorder(null);
        valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorActionPerformed(evt);
            }
        });
        valor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                valorKeyTyped(evt);
            }
        });
        add(valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 242, 230, 35));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel11.setText("Forma de Pagamento");
        add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 360, 170, 20));

        textField5.setBorder(null);
        textField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textField5ActionPerformed(evt);
            }
        });
        add(textField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 382, 230, 35));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel3.setText("Data de Entrada");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 120, 20));

        datePicker1.setEditable(false);
        datePicker1.setBackground(new java.awt.Color(255, 255, 255));
        datePicker1.setBorder(null);
        datePicker1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datePicker1MouseClicked(evt);
            }
        });
        datePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datePicker1ActionPerformed(evt);
            }
        });
        add(datePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 172, 150, 35));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel12.setText("Faturável");
        add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 290, 160, 20));

        datePicker2.setEditable(false);
        datePicker2.setBackground(new java.awt.Color(255, 255, 255));
        datePicker2.setBorder(null);
        datePicker2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datePicker2MouseClicked(evt);
            }
        });
        add(datePicker2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 242, 150, 35));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel13.setText("Data de Vencimento");
        add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 160, 20));

        choice1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        choice1.setMinimumSize(new java.awt.Dimension(28, 40));
        add(choice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 160, 30));

        choice3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        choice3.setMinimumSize(new java.awt.Dimension(28, 40));
        add(choice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 230, 30));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel4.setText("Fornecedor");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 100, 20));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 170, 40));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 140, 40));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 170, 40));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 140, 40));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 170, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 140, 40));

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, 170, 40));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 140, 40));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 140, 40));

        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 380, 170, 40));

        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 170, 90, 40));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 170, 140, 40));

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 240, 90, 40));

        jLabel25.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/Box.png"))); // NOI18N
        add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 140, 40));

        alert.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        alert.setForeground(new java.awt.Color(204, 0, 0));
        alert.setText("Preencha todos os campos!");
        add(alert, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 230, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void textField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField1ActionPerformed

    private void valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valorActionPerformed

    private void textField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textField5ActionPerformed

    private void datePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datePicker1ActionPerformed

    }//GEN-LAST:event_datePicker1ActionPerformed

    private void datePicker1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datePicker1MouseClicked
        try {
            dataPicker.show(true);
        } catch (Exception e) {
            dataPicker = new DataPicker(datePicker1);
        }
    }//GEN-LAST:event_datePicker1MouseClicked

    private void datePicker2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datePicker2MouseClicked
        try {
            dataPicker1.show(true);
        } catch (Exception e) {
            dataPicker1 = new DataPicker(datePicker2);
        }
    }//GEN-LAST:event_datePicker2MouseClicked

    private void valorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorKeyTyped
        valor.setText(InputTools.makeDecimal(valor.getText(), evt, evt.getKeyChar()+""));
        evt.consume();
    }//GEN-LAST:event_valorKeyTyped

    private void textField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField4KeyTyped
        textField4.setText(InputTools.acceptInteger(textField4.getText(), evt, evt.getKeyChar()+""));
        evt.consume();
    }//GEN-LAST:event_textField4KeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alert;
    private java.awt.Choice choice1;
    private java.awt.Choice choice3;
    private javax.swing.JTextField datePicker1;
    private javax.swing.JTextField datePicker2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField textField1;
    private javax.swing.JTextField textField2;
    private javax.swing.JTextField textField4;
    private javax.swing.JTextField textField5;
    private javax.swing.JTextField valor;
    // End of variables declaration//GEN-END:variables
}
