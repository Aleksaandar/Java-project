/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package forme;

import communication.KontrolerKorisnickogInterfejsa;
import controller.Controller;
import domain.Admin;
import domain.Jezik;
import domain.Student;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import model.AdminTableModel;
import model.JezikTableModel;
import server.Server;
import server.ServerConfig;

/**
 *
 * @author Hrckok
 */
public class ServerskaForma extends javax.swing.JFrame {

    private Thread serverThread;
    private Server server;
    private List<Admin> admini;
    private List<Jezik> jezici;
    private List<Student> studenti;

    /**
     * Creates new form ServerskaForma
     */
    public ServerskaForma() {
        initComponents();
        jButtonZaustaviServer.setEnabled(false);
        this.setTitle("Škola stranih jezika Poliglota");
        admini = new ArrayList<>();
        tblActiveUsers.setModel(new AdminTableModel(admini));
        adminListener();
        this.setLocationRelativeTo(null);
        try {
            prepareTable();
        } catch (Exception ex) {
            Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
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

        jButtonPokreniServer = new javax.swing.JButton();
        jlblPokreniServer = new javax.swing.JLabel();
        jButtonZaustaviServer = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblActiveUsers = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableJezici = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButtonPokreniServer.setText("Pokreni server");
        jButtonPokreniServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPokreniServerActionPerformed(evt);
            }
        });

        jlblPokreniServer.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jlblPokreniServer.setForeground(new java.awt.Color(255, 0, 0));
        jlblPokreniServer.setText("SERVER NIJE POKRENUT!");

        jButtonZaustaviServer.setText("Zaustavi server");
        jButtonZaustaviServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonZaustaviServerActionPerformed(evt);
            }
        });

        tblActiveUsers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblActiveUsers);

        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 0, 18)); // NOI18N
        jLabel1.setText("Trenutno ulogovani admini");

        jTableJezici.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableJezici);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(103, 103, 103)
                            .addComponent(jlblPokreniServer))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jButtonPokreniServer)
                            .addGap(43, 43, 43)
                            .addComponent(jButtonZaustaviServer)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonPokreniServer)
                    .addComponent(jButtonZaustaviServer))
                .addGap(52, 52, 52)
                .addComponent(jlblPokreniServer)
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPokreniServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPokreniServerActionPerformed
        try {
            startServer();
            jlblPokreniServer.setText("SERVER JE POKRENUT!");
            jlblPokreniServer.setForeground(Color.BLUE);
        } catch (IOException ex) {
            Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonPokreniServerActionPerformed

    private void jButtonZaustaviServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonZaustaviServerActionPerformed
        stopServer();
        jlblPokreniServer.setText("SERVER NIJE POKRENUT!");
        jlblPokreniServer.setForeground(Color.RED);
    }//GEN-LAST:event_jButtonZaustaviServerActionPerformed

    private void startServer() throws IOException {
        ServerConfig config = new ServerConfig("src\\properties\\database_properties.properties");
        server = new Server(config);
        Controller.getInstance().setServer(server); // Postavljanje servera

        if (server.start()) {
            serverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    server.listen();
                }
            });
            serverThread.start();
            System.out.println("Server thread started."); // Logovanje
            jButtonPokreniServer.setEnabled(false);
            jButtonZaustaviServer.setEnabled(true);
            JOptionPane.showMessageDialog(this, "Server je pokrenut!");
        } else {
            JOptionPane.showMessageDialog(this, "Server je već pokrenut ili je došlo do greške prilikom pokretanja.");
        }
    }

    private void stopServer() {
        if (server != null) {
            System.out.println("Calling server.stop()...");
            server.stop();
            try {
                if (serverThread != null && serverThread.isAlive()) {
                    serverThread.join(); // Wait for the server thread to finish
                    System.out.println("Server thread joined.");
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    jButtonPokreniServer.setEnabled(true);
                    jButtonZaustaviServer.setEnabled(false);
                    JOptionPane.showMessageDialog(ServerskaForma.this, "Server je zaustavljen!");
                }
            });
        } else {
            System.out.println("Server is null, cannot stop.");
        }
    }

    public void prepareTable() throws Exception {
        int brojac=0;
        jezici = Controller.getInstance().ucitajSveJezike();
        studenti=Controller.getInstance().ucitajSveStudente();
        for (Jezik jezik : jezici) {
             brojac=0;
            for (Student student : studenti) {
              if(student.getJezik().getJezikID()==jezik.getJezikID()){
                  ++brojac;
                  jezik.setBrojStudenataKojiSlusaju(brojac);
              }  
            }
        }
        jTableJezici.setModel(new JezikTableModel(jezici));

    }

    private void adminListener() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        prepareView();
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ServerskaForma.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }).start();
    }

    private void prepareView() {
        if (server != null) {
            admini = server.getUlogovaniAdmini();
            if (admini == null) {
                tblActiveUsers.setModel(new AdminTableModel());
                return;
            }
            System.out.println(admini);
            tblActiveUsers.setModel(new AdminTableModel(admini));
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerskaForma.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerskaForma().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonPokreniServer;
    private javax.swing.JButton jButtonZaustaviServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableJezici;
    private javax.swing.JLabel jlblPokreniServer;
    private javax.swing.JTable tblActiveUsers;
    // End of variables declaration//GEN-END:variables
}
