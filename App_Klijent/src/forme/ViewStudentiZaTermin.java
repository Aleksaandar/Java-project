/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package forme;

import communication.KontrolerKorisnickogInterfejsa;
import domain.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.StudentTableModel;

/**
 *
 * @author Hrckok
 */
public class ViewStudentiZaTermin extends javax.swing.JDialog {

    List<Student> studenti;
    Student ucitanStudent;

    /**
     * Creates new form ViewStudentiZaTermin
     */
    public ViewStudentiZaTermin(javax.swing.JDialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        prepareTable();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    public Student getUcitanStudent() {
        return ucitanStudent;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudents = new javax.swing.JTable();
        txtByName = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jButton1.setText("UČITAJ STUDENTA");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        tblStudents.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblStudents);

        jLabel1.setText("Pretrazi po imenu:");

        jButton2.setText("Pretraži");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
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
                        .addGap(38, 38, 38)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtByName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(140, 140, 140)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(156, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = tblStudents.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Morate izabrati studenta!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int option = JOptionPane.showConfirmDialog(this, "Jeste li sigurni da zelite da ucitate datog studenta?", "Load confirmation", JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION) {
            System.out.println(option);

            try {
                StudentTableModel model = (StudentTableModel) tblStudents.getModel();
                Student student = model.getStudents().get(row);
               
                ucitanStudent = KontrolerKorisnickogInterfejsa.getInstance().ucitajStudenta(student);

                JOptionPane.showMessageDialog(this, "Student je učitan!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
   if (!txtByName.getText().isEmpty()) {
            filterByName(txtByName.getText().trim());
        } else {
            prepareTable();
        }

    }//GEN-LAST:event_jButton2ActionPerformed
   public void prepareTable() {
        studenti = KontrolerKorisnickogInterfejsa.getInstance().ucitajSveStudente();
        tblStudents.setModel(new StudentTableModel(studenti));

    }
   
    private void filterByName(String query) {
        Student st = new Student();
        st.setIme(query);
        try {
            ArrayList<Student> studenti = KontrolerKorisnickogInterfejsa.getInstance().ucitajSveStudenteUslov(st);
            System.out.println(studenti);

            if (studenti.size() != 0) {
                ((StudentTableModel) tblStudents.getModel()).setStudents(studenti);
                JOptionPane.showMessageDialog(this, "Sistem je našao studenta po zadatoj vrednosti!");
                tblStudents.setModel(new StudentTableModel(studenti));

            } else {
                JOptionPane.showMessageDialog(this, "Sistem ne može da nađe studenta po zadatoj vrednosti!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);

            Logger.getLogger(ViewStudenti.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblStudents;
    private javax.swing.JTextField txtByName;
    // End of variables declaration//GEN-END:variables
}
