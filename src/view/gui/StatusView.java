/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * StatusView.java
 *
 * Created on Jan 13, 2012, 4:33:07 PM
 */
package view.gui;

import control.gui.StatusControl;

/**
 *
 * @author drew
 */
public class StatusView extends javax.swing.JPanel {

    private StatusControl statControl;
    
    /** Creates new form StatusView */
    public StatusView() {
        initComponents();
    }
    
    public void setStatusController(StatusControl control)
    {
        statControl = control;
        // now get the data to fill the table and add it to the tab
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tournTabs = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tournTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tournTabs, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tournTabs;
    // End of variables declaration//GEN-END:variables
}