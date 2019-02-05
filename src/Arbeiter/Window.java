package Arbeiter;
//Importierte file
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
//UnserHaupt class geerbt von JFrame

public class Window extends javax.swing.JFrame {

    int mausX;
    int mausY;
    String ImgPath = null;
    int pos = 0;
    boolean selected = false;
    DefaultTableModel model;
    Connection con = getConnection();
    PreparedStatement ps;
    Statement st = null;
    ResultSet rs = null;

    public Window() {
        setUndecorated(true);
        initComponents();
        getConnection();
        Show_Product_In_JTable();
        // idF.setEnabled(false);
    }
// Connection Methode Mit Unser Datenbank
    public Connection getConnection() {
//Variablen als Connection deklarieren und Initialisiere
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/lolo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
                    "root", //unser User Accounts
                    "");     //unser Passwort
        } catch (SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
// prüfen eingabe Filder

    public boolean checkInputs() {
        if (nameF.getText() == null
                || lohnF.getText() == null
                
                || imageL == null) {
            return false;
        } else {
            try {
                //Convert to Float
                Float.parseFloat(lohnF.getText());
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }
//  Resize Image
    public ImageIcon ResizeImage(String imagePath, byte[] pic) {
        ImageIcon myImage = null;
        if (imagePath != null) {
            myImage = new ImageIcon(imagePath);
        } else {
            myImage = new ImageIcon(pic);
        }
        //Nehmt die große von Unser Frame
        Image img = myImage.getImage();
        Image img2 = img.getScaledInstance(imageL.getWidth(),
                imageL.getHeight(),
                Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(img2);
        return image;
    }
//machen liste damit können wir bei tabele benutzen:
    public ArrayList<Arbeiter> getProductList() {
        //die ArrayList nehmt die info von unser Arbeiter Klasse
        ArrayList<Arbeiter> productList = new ArrayList<Arbeiter>();
        String query = "SELECT * FROM Arbeiter";
        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Arbeiter product;
            while (rs.next()) {
                product = new Arbeiter(rs.getInt("Id"), rs.getString("Name"), Float.parseFloat(rs.getString("Lohn")), rs.getString("Date"), rs.getBytes("Image"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }
//Zeigen Unser gespeicherte Info in die Tabele
    public void Show_Product_In_JTable() {
        ArrayList<Arbeiter> list = getProductList();
        model = (DefaultTableModel) tableJ.getModel();
//nur ein Row Addieren
        model.setRowCount(0);
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getLohn();
            row[3] = list.get(i).getDate();
            model.addRow(row);
            // hier addiert nur ein Row und nicht alle Wieder
            tableJ.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        }
    }
//Info Zeigen auf den Filder
//عرض ال

    public void ShowItem(int index) {
        idF.setText(Integer.toString(getProductList().get(index).getId()));
        nameF.setText(getProductList().get(index).getName());
        lohnF.setText(Float.toString(getProductList().get(index).getLohn()));      
        if (getProductList().get(index).getImage() != null) {
            imageL.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
        } else {
            imageL.setIcon(null);
        }
        //zeigt die selected row mit farbe
        tableJ.setSelectionBackground(Color.RED);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nameF = new javax.swing.JTextField();
        idF = new javax.swing.JTextField();
        lohnF = new javax.swing.JTextField();
        imageL = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableJ = new javax.swing.JTable();
        BWahl = new javax.swing.JButton();
        deleteB = new javax.swing.JButton();
        insertB = new javax.swing.JButton();
        updateB = new javax.swing.JButton();
        lastB = new javax.swing.JButton();
        nextB = new javax.swing.JButton();
        previosB = new javax.swing.JButton();
        firstB = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 53, 122));
        jPanel1.setForeground(new java.awt.Color(255, 204, 255));
        jPanel1.setToolTipText("");
        jPanel1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                jPanel1MouseDragged(evt);
            }
        });
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jPanel1MousePressed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Name:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Lohn:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Image:");

        nameF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        idF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idFActionPerformed(evt);
            }
        });

        lohnF.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lohnF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lohnFActionPerformed(evt);
            }
        });
        lohnF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lohnFKeyTyped(evt);
            }
        });

        imageL.setBackground(new java.awt.Color(204, 255, 255));
        imageL.setOpaque(true);

        tableJ.setBackground(new java.awt.Color(204, 204, 255));
        tableJ.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "DATE"
            }
        ));
        tableJ.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableJMouseClicked(evt);
            }
        });
        tableJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableJKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tableJ);

        BWahl.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        BWahl.setText("Image Wahl");
        BWahl.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BWahl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BWahlActionPerformed(evt);
            }
        });

        deleteB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        deleteB.setText("Delete");
        deleteB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBActionPerformed(evt);
            }
        });

        insertB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        insertB.setText("Insert");
        insertB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        insertB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBActionPerformed(evt);
            }
        });

        updateB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        updateB.setText("Update");
        updateB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updateB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBActionPerformed(evt);
            }
        });

        lastB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lastB.setText("Last");
        lastB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lastB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lastBActionPerformed(evt);
            }
        });

        nextB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nextB.setText("Next");
        nextB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextBActionPerformed(evt);
            }
        });

        previosB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        previosB.setText("Previos");
        previosB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        previosB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previosBActionPerformed(evt);
            }
        });

        firstB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        firstB.setText("First");
        firstB.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        firstB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                firstBActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 0));
        jLabel6.setText("x");
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 153, 0));
        jLabel7.setText("-");
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BWahl, javax.swing.GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                            .addComponent(nameF)
                            .addComponent(lohnF)
                            .addComponent(imageL, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(idF, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 127, Short.MAX_VALUE)))
                        .addGap(138, 138, 138))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(insertB, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteB, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(firstB, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(previosB))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(nextB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastB, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(159, 159, 159))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(71, 71, 71)
                                    .addComponent(idF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(4, 4, 4))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nameF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lohnF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(33, 33, 33)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(imageL, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(37, 37, 37)
                            .addComponent(BWahl))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateB)
                        .addComponent(insertB)
                        .addComponent(deleteB))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(previosB)
                        .addComponent(nextB)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(firstB)
                    .addComponent(lastB))
                .addGap(112, 112, 112))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
//Löchen Button
    private void deleteBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBActionPerformed
        //Zeig mir Massege confirm ob ich sicher bin oder nicht
        int i = JOptionPane.showConfirmDialog(null, "Sind Sie sicher?", "Sicherheit Frage", JOptionPane.OK_CANCEL_OPTION);
        if (i == JOptionPane.OK_OPTION) {
            if (!idF.getText().equals("")) {
                try {
                    ps = con.prepareStatement("DELETE FROM Arbeiter WHERE ID=?");
                    int id = Integer.parseInt(idF.getText());
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    Show_Product_In_JTable();                                   
                } catch (SQLException ex) {
                    Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                    //Zeig mir Massege
                    JOptionPane.showMessageDialog(null, "Ware ist nicht gelöscht");
                }
            } else if (i == JOptionPane.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(null, "alles klar", "Sicherheit Frage", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteBActionPerformed
//Bilder Wählen Button
    private void BWahlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BWahlActionPerformed
        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        //unser Filter sucht nur mit diese art von file
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        //hier könenn wir unser folder ofnnen
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            imageL.setIcon(ResizeImage(path, null));
            ImgPath = path;
        } else {
            System.out.println("No File Selected");
        }
    }//GEN-LAST:event_BWahlActionPerformed
    //diese Methode prüft ob die Name schon da 
    public boolean isSameName() {
        //query prüft unser DatenBank
        String query = "SELECT * FROM arbeiter WHERE Name=?";
        try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1, nameF.getText());
            rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
//Addieren Button
    private void insertBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBActionPerformed
        //wenn Name nicht da vorher
        if (isSameName() == true) {
            JOptionPane.showMessageDialog(null, "Sie habe sie Name Schon gelegt");
            return;
        }
        //Hier Addieren Ohne Bilder
        //wenn ferlder nicht leer und image leer und datum nicht leer
        if (checkInputs() == true && ImgPath == null ) {

            try {
                ps = con.prepareStatement("INSERT INTO Arbeiter(Name,Lohn,Date)values(?,?,?)");
                ps.setString(1, nameF.getText());
                ps.setString(2, lohnF.getText());              
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Informationen ohne Bild hinzugefügt");
                Show_Product_In_JTable();
            } catch (Exception ex) {
                ex.getMessage();
            }
            //Hier Addieren Mit Bilder 
        } else if (checkInputs() == true && ImgPath != null ) {

            try {
                ps = con.prepareStatement("INSERT INTO Arbeiter(Name,Lohn,Date,image)values(?,?,?,?)");
                ps.setString(1, nameF.getText());
                ps.setString(2, lohnF.getText());
                InputStream img = new FileInputStream(new File(ImgPath));
                ps.setBlob(4, img);
                ps.executeUpdate();
                /*Nach Insert Zeigt Uns neue user*/
                Show_Product_In_JTable();
                JOptionPane.showMessageDialog(null, "Informationen mit Bild hinzugefügt");
            } catch (Exception ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(null, "ein oder mehr felder sind nicht da");
        }
    }//GEN-LAST:event_insertBActionPerformed
//Ubdate Button
    private void updateBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBActionPerformed
        String updateQuery;
        if (checkInputs() == true&&!nameF.getText().isEmpty() && idF != null && ImgPath == null) {

//Update Ohne image
            try {
                updateQuery = "UPDATE Arbeiter SET Name=?,Lohn=?,Date=?WHERE ID=?";
                ps = con.prepareStatement(updateQuery);
                ps.setString(1, nameF.getText());
                ps.setString(2, lohnF.getText());            
                ps.setInt(4, Integer.parseInt(idF.getText()));           
       int i=  JOptionPane.showConfirmDialog(null,"Sind Sie sicher? ","Sicherheit Frage",JOptionPane.OK_CANCEL_OPTION);
       if(i==JOptionPane.CANCEL_OPTION){return;}
       else{
                ps.executeUpdate();
                Show_Product_In_JTable();}
                //Zeig mir Massege
                JOptionPane.showMessageDialog(null, "Info geändert");
            } catch (SQLException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
                       }
        } //Update Mit  image
        else if (checkInputs() == true &&!nameF.getText().isEmpty()&& idF != null && ImgPath != null) {
            InputStream img;
            try {
                img = new FileInputStream(new File(ImgPath));
                updateQuery = "UPDATE Arbeiter SET Name=?,Lohn=?,Date=?,image=? WHERE ID=?";
                ps = con.prepareStatement(updateQuery);
                ps.setString(1, nameF.getText());
                ps.setString(2, lohnF.getText());
                
                ps.setBlob(4, img);
                ps.setInt(5, Integer.parseInt(idF.getText()));
                int i=  JOptionPane.showConfirmDialog(null,"Sind Sie sicher? ","Sicherheit Frage",JOptionPane.OK_CANCEL_OPTION);
       if(i==JOptionPane.CANCEL_OPTION){return;}
       else{
                ps.executeUpdate();
                Show_Product_In_JTable();}
                //Zeig mir Massege
                JOptionPane.showMessageDialog(null, "Info geändert");
            } catch (Exception e) {
                e.getMessage();
            }
        } else {
            //Zeig mir Massege
            JOptionPane.showMessageDialog(null, "eine oder mehr fehlt");
        }
    }//GEN-LAST:event_updateBActionPerformed
//Maus click bei Tebel
    private void tableJMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableJMouseClicked
        int index = tableJ.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_tableJMouseClicked
//First Button geht zu erste Row In der Tabele
    private void firstBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_firstBActionPerformed
        pos = 0;
        pos = tableJ.getSelectedRow();
        tableJ.setRowSelectionInterval(pos = 0, pos = 0);
        ShowItem(pos);
    }//GEN-LAST:event_firstBActionPerformed
//Last Button geht zu Letzt Row In der Tabele
    private void lastBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lastBActionPerformed
        pos = getProductList().size() - 1;
        tableJ.setRowSelectionInterval(pos, pos);
        ShowItem(pos);
    }//GEN-LAST:event_lastBActionPerformed
    //Next Button geht nach unten 
    private void nextBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextBActionPerformed
        pos = tableJ.getSelectedRow();
        if (pos < getProductList().size() - 1) {
            pos++;
        }
        tableJ.setRowSelectionInterval(pos, pos);
        ShowItem(pos);
    }//GEN-LAST:event_nextBActionPerformed
//Previos Button geht nach oben
    private void previosBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previosBActionPerformed
        pos = tableJ.getSelectedRow();
        if (pos > 0) {
            pos--;
        }
        tableJ.setRowSelectionInterval(pos, pos);
        ShowItem(pos);
//tableJ.setSelectionBackground(Color.BLUE);
    }//GEN-LAST:event_previosBActionPerformed

    private void idFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idFActionPerformed

    }//GEN-LAST:event_idFActionPerformed

    private void lohnFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lohnFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lohnFActionPerformed

// mit Diese Methode kann man Exit der Programm
    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel6MouseClicked
// mit Diese Methode kann man den Frame Minimize
    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_jLabel7MouseClicked
//mit diese methode kann mann den Frame bewegen
    private void jPanel1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MousePressed
        int x = evt.getX();
        int y = evt.getY();
    }//GEN-LAST:event_jPanel1MousePressed
    //mit diese methode kann mann den Frame bewegen
    private void jPanel1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - mausX, y - mausY);
    }//GEN-LAST:event_jPanel1MouseDragged
    // mit diese Methode kann man nur mit Digital schreiben
    private void lohnFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lohnFKeyTyped
        char c = evt.getKeyChar();
        if (!(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)) {
            evt.consume();
        }
    }//GEN-LAST:event_lohnFKeyTyped

    private void tableJKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableJKeyReleased
        int index = tableJ.getSelectedRow();
        ShowItem(index);
    }//GEN-LAST:event_tableJKeyReleased
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BWahl;
    private javax.swing.JButton deleteB;
    private javax.swing.JButton firstB;
    private javax.swing.JTextField idF;
    private javax.swing.JLabel imageL;
    private javax.swing.JButton insertB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lastB;
    private javax.swing.JTextField lohnF;
    private javax.swing.JTextField nameF;
    private javax.swing.JButton nextB;
    private javax.swing.JButton previosB;
    private javax.swing.JTable tableJ;
    private javax.swing.JButton updateB;
    // End of variables declaration//GEN-END:variables
}
