package gestaopet.view.home;
import gestaopet.classes.GlobalPanel;
import gestaopet.classes.Navegation;
import gestaopet.view.reservas.PainelReserva;
import gestaopet.view.servicos.PainelServico;

public class StartPage extends GlobalPanel {
    private PainelReserva listRes = new PainelReserva(false);
    private PainelServico listSer = new PainelServico(true);
    private Navegation nv;
    private Navegation nv2;
    private String param;
        
    
    public StartPage(int param) {
        initComponents();
        this.param = param + "";
        nv = new Navegation(area1, target1, listRes);
        nv.setViewPanel(10, 520, 570);
        nv2 = new Navegation(area2, target2, listSer);
        nv2.setViewPanel(10, 500, 570);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        area1 = new javax.swing.JPanel();
        target1 = new javax.swing.JPanel();
        area2 = new javax.swing.JPanel();
        target2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        shadow = new javax.swing.JLabel();
        shadow1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("10"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        area1.setBackground(new java.awt.Color(255, 255, 255));
        area1.setName("10"); // NOI18N
        area1.setOpaque(false);

        target1.setBackground(new java.awt.Color(255, 153, 153));

        javax.swing.GroupLayout target1Layout = new javax.swing.GroupLayout(target1);
        target1.setLayout(target1Layout);
        target1Layout.setHorizontalGroup(
            target1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 427, Short.MAX_VALUE)
        );
        target1Layout.setVerticalGroup(
            target1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout area1Layout = new javax.swing.GroupLayout(area1);
        area1.setLayout(area1Layout);
        area1Layout.setHorizontalGroup(
            area1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area1Layout.createSequentialGroup()
                .addComponent(target1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        area1Layout.setVerticalGroup(
            area1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area1Layout.createSequentialGroup()
                .addComponent(target1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        add(area1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 490, 440));

        area2.setBackground(new java.awt.Color(255, 255, 255));
        area2.setName("10"); // NOI18N
        area2.setOpaque(false);

        target2.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout target2Layout = new javax.swing.GroupLayout(target2);
        target2.setLayout(target2Layout);
        target2Layout.setHorizontalGroup(
            target2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 478, Short.MAX_VALUE)
        );
        target2Layout.setVerticalGroup(
            target2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout area2Layout = new javax.swing.GroupLayout(area2);
        area2.setLayout(area2Layout);
        area2Layout.setHorizontalGroup(
            area2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(area2Layout.createSequentialGroup()
                .addComponent(target2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        area2Layout.setVerticalGroup(
            area2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(target2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        add(area2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 60, 510, 440));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(80, 176, 230));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Checkins & Checkouts");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 0, 390, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 188, 86));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Serviços");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 400, 40));

        shadow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/listaAcabamento.png"))); // NOI18N
        add(shadow, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 480, 490, 40));

        shadow1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/listaAcabamento.png"))); // NOI18N
        add(shadow1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, 540, 40));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel area1;
    private javax.swing.JPanel area2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel shadow;
    private javax.swing.JLabel shadow1;
    private javax.swing.JPanel target1;
    private javax.swing.JPanel target2;
    // End of variables declaration//GEN-END:variables
}
