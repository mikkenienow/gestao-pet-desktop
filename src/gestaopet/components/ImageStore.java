package gestaopet.components;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageStore extends javax.swing.JPanel {
    private File swapFile = null;
    private String foto = "none";
    private String defaultPath = "C:\\gestaopet\\Fotos\\";
    private String tempFile = "C:\\gestaopet\\Temp\\tempfile.png";
    public ImageIcon loadedImage;
    private boolean fotoToSave = true;

    public ImageStore(){
        initComponents();
    }
    
    public ImageStore(boolean showLabel){
        initComponents();
        jLabel1.setVisible(showLabel);
    }
    
    public void setBlank(){
        swapFile = null;
        foto = "none";
        setDisplay(true, imgDisplay);
    }
    
    public ImageIcon selectImage(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Imagens PNG e JPG", "PNG", "JPG");
        fc.setFileFilter(filter);
        int res = fc.showOpenDialog(this);
        ImageIcon image = loadedImage;
        if(res == JFileChooser.APPROVE_OPTION){
            File f = fc.getSelectedFile(); 
            swapFile = fc.getSelectedFile();
            String fileName = "";

            try {
                fileName = f.getAbsolutePath();
            } catch (Exception e) {
            }

            if(fileName.indexOf("jpg") > -1){
                convertToPng(fileName);
                image = new ImageIcon(tempFile);
                foto = "selected";
            } else {
                image = new ImageIcon(fileName);
                foto = "selected";

            }
            fotoToSave = true;
        }
        
        
        
        return image;
    }
    
    public ImageIcon imageResize(ImageIcon image, int width, int height){
        Image originalImage = image.getImage();
        Image resizedImage = originalImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        ImageIcon output = new ImageIcon(resizedImage);
        return output;        
    }

    public void copy(String fileName){
        try {
            loadedImage.getImage();
        } catch (Exception e) {
            if(foto.equals("none")){
            } else {
                String path = swapFile.getAbsolutePath().replaceAll("/", "//");
                if(path.indexOf("jpg") > -1){
                    File f = new File(tempFile);
                    File f2 = new File(defaultPath + fileName + ".png");
                    checkFileAndDelete(f2);
                    f.renameTo(new File(defaultPath + fileName + ".png"));
                } else {
                    File f = new File(path);
                    File f2 = new File(defaultPath + fileName + ".png");
                    checkFileAndDelete(f2);
                    f.renameTo(new File(defaultPath + fileName + ".png"));
                }
            }
        }
        
    }
    
    public void checkFileAndDelete(File f){
        if(f.exists()){ 
            f.delete();
        }
    }
    
    
    public void openFoto(String nome, JLabel target){
        this.foto = nome;
        fotoToSave = false;
        if(nome.equals("none")){
            target.setIcon(null);
        } else {
            String path = defaultPath + nome + ".png";
            ImageIcon image = new ImageIcon(path);
            loadedImage = image;
            target.setIcon(imageResize(image, 200, 200));
        }
    }
    
    public void openFotoResizer(String nome, JLabel target, int width, int height){
        this.foto = nome;
        if(nome.equals("none")){
            target.setIcon(null);
        } else {
            String path = defaultPath + nome + ".png";
            ImageIcon image = new ImageIcon(path);
            target.setIcon(imageResize(image, width, height));
        }
    }
    
    public boolean getFoto(){
        if(foto.equals("none") || !fotoToSave){
            return false;
        } else {
            return true;
        }
    }
    
    public void setDisplay(boolean blank, JLabel target){
        if(blank){
            target.setIcon(null);
            foto = "none";
        } else {
            target.setIcon(imageResize(selectImage(), 200, 200));
        }
    }
    
    public void convertToPng(String jpgPath){
        Path source = Paths.get(jpgPath); //JPG
        Path target = Paths.get(tempFile); //PNG
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(source.toFile());
        } catch (Exception e) {
            System.out.println("erro read " + e);
        }


        BufferedImage newBufferedImage = null;
        try {
            newBufferedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB);
        } catch (Exception e) {
            System.out.println("erro img " + e);
        }
                

        newBufferedImage.createGraphics()
                .drawImage(originalImage,
                        0,
                        0,
                        Color.WHITE,
                        null);
        try {
            ImageIO.write(newBufferedImage, "png", target.toFile());
        } catch (Exception e) {
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        abrirImagemButton = new javax.swing.JButton();
        salvarImagemButton = new javax.swing.JButton();
        imgDisplay = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setName("10"); // NOI18N
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        abrirImagemButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/edit0.png"))); // NOI18N
        abrirImagemButton.setBorder(null);
        abrirImagemButton.setBorderPainted(false);
        abrirImagemButton.setContentAreaFilled(false);
        abrirImagemButton.setIconTextGap(0);
        abrirImagemButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        abrirImagemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirImagemButtonActionPerformed(evt);
            }
        });
        add(abrirImagemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 40, -1));

        salvarImagemButton.setText("Salvar imagem");
        salvarImagemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarImagemButtonActionPerformed(evt);
            }
        });
        add(salvarImagemButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 662, 200, -1));

        imgDisplay.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        imgDisplay.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        imgDisplay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(15, 59, 146), 2));
        add(imgDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 200, 200));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/PetImg 250.png"))); // NOI18N
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gestaopet/tema/icons/petSilhouette.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 40, 140, 150));
    }// </editor-fold>//GEN-END:initComponents

    private void abrirImagemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirImagemButtonActionPerformed
        setDisplay(false, imgDisplay);
        
    }//GEN-LAST:event_abrirImagemButtonActionPerformed

    private void salvarImagemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarImagemButtonActionPerformed

    }//GEN-LAST:event_salvarImagemButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abrirImagemButton;
    public javax.swing.JLabel imgDisplay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton salvarImagemButton;
    // End of variables declaration//GEN-END:variables
}
