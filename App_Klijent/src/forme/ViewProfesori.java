/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import communication.KontrolerKorisnickogInterfejsa;
import domain.Profesor;
import domain.Student;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.ProfesorTableModel;

/**
 *
 * @author Hrckok
 */
public class ViewProfesori extends javax.swing.JDialog {

    List<Profesor> profesori;

    /**
     * Creates new form ViewProfesori
     */
    public ViewProfesori(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepareTable();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.s
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnViewAllProfessors = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProfessors = new javax.swing.JTable();
        btnEditProfessor = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtByName = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Pretrazi po imenu:");

        btnViewAllProfessors.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnViewAllProfessors.setText("VIDI SVE PROFESORE");
        btnViewAllProfessors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnViewAllProfessorsActionPerformed(evt);
            }
        });

        tblProfessors.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblProfessors);

        btnEditProfessor.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnEditProfessor.setText("IZMENI PROFESORA");
        btnEditProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfessorActionPerformed(evt);
            }
        });

        jButton2.setText("Pretraži");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        txtByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtByNameActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtByName, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 805, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnViewAllProfessors, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnViewAllProfessors, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditProfessor, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnViewAllProfessorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnViewAllProfessorsActionPerformed
        prepareTable();
    }//GEN-LAST:event_btnViewAllProfessorsActionPerformed

    private void btnEditProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfessorActionPerformed
        int selectedRow = tblProfessors.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Niste selektovali profesora iz tabele! !");
            return;
        }
        ProfesorTableModel model = (ProfesorTableModel) tblProfessors.getModel();
        Profesor profesor = model.getProfessors().get(selectedRow);
        System.out.println(profesor);
        Profesor loadProfesor = new Profesor();
        loadProfesor = KontrolerKorisnickogInterfejsa.getInstance().ucitajProfesora(profesor);
        if (loadProfesor != null) {
            JOptionPane.showMessageDialog(this, "Sistem je uspešno ucitao profesora .");
            IzmeniProfesora izmeniProfesora = new IzmeniProfesora((Frame) this.getParent(), true, loadProfesor, this);
            izmeniProfesora.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Sistem ne može da ucita profesora.", "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditProfessorActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (!txtByName.getText().isEmpty()) {
            filterByName(txtByName.getText().trim());
        } else {
            prepareTable();

        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtByNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtByNameActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditProfessor;
    private javax.swing.JButton btnViewAllProfessors;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblProfessors;
    private javax.swing.JTextField txtByName;
    // End of variables declaration//GEN-END:variables

    public void prepareTable() {
        profesori = KontrolerKorisnickogInterfejsa.getInstance().ucitajSveProfesore();
        tblProfessors.setModel(new ProfesorTableModel(profesori));
    }

    private void filterByName(String query) {
        Profesor ps = new Profesor();
        ps.setIme(query);
        try {
            ArrayList<Profesor> profesori = KontrolerKorisnickogInterfejsa.getInstance().ucitajSveProfesoreUslov(ps);
            System.out.println(profesori);

            if (profesori.size() != 0) {
                ((ProfesorTableModel) tblProfessors.getModel()).setProfessors(profesori);
                JOptionPane.showMessageDialog(this, "Sistem je našao profesora po zadatoj vrednosti!");
                tblProfessors.setModel(new ProfesorTableModel(profesori));

            } else {
                JOptionPane.showMessageDialog(this, "Sistem ne može da nađe profesora po zadatoj vrednosti!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(ViewStudenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
