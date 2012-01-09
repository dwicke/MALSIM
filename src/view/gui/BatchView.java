/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BatchView.java
 *
 * Created on Jan 7, 2012, 9:57:21 PM
 */
package view.gui;

import control.gui.BatchControl;
import java.rmi.RemoteException;
import util.Subscriber;

/**
 *
 * @author drew
 */
public class BatchView extends javax.swing.JFrame implements Subscriber {

    public static BatchControl controller;
    public static boolean isEditable;
    public static BatchView view;
    private int count;

    /** Creates new form BatchView */
    public BatchView() {

        initComponents();
        count = 1;
        batchButtonPannel1.addSub(this);
        addTournament();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tournTabPane = new javax.swing.JTabbedPane();
        batchButtonPannel1 = new view.gui.BatchButtonPannel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Batch Creator");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tournTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
            .addComponent(batchButtonPannel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tournTabPane, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(batchButtonPannel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        view = null;
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(BatchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BatchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BatchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BatchView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                view = new BatchView();
                view.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.gui.BatchButtonPannel batchButtonPannel1;
    private javax.swing.JTabbedPane tournTabPane;
    // End of variables declaration//GEN-END:variables

    /**
     * This will add a tournament tab to the tab pain and set up the
     * background model data.
     */
    private void addTournament() {
        TournamentPropertiesView propview = new TournamentPropertiesView();

        propview.setTournControl(controller.getNewTournControl());
        tournTabPane.addTab("Tournament " + count, propview);
        count++;
    }

    /**
     * This will remove the tournament from the batch
     * and it will remove the tab from the view.
     */
    private void removeTournament() {
        if (tournTabPane.getSelectedComponent() != null) {
            controller.removeTourn(((TournamentPropertiesView) tournTabPane.getSelectedComponent()).getTournControl().getTournament());
            tournTabPane.remove(tournTabPane.getSelectedComponent());
            
        }
    }

    private void loadTournaments() {
    }

    private void saveBatch() {
    }

    private void runBatch() {
    }

    @Override
    public void update(Object pub, Object code) throws RemoteException {

        if (code.equals("add")) {
            addTournament();
        } else if (code.equals("remove")) {
            removeTournament();
        } else if (code.equals("save")) {
            saveBatch();
        } else if (code.equals("run")) {
            runBatch();
        }
    }
}