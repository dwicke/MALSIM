/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PropertyView.java
 *
 * Created on Jan 10, 2012, 3:25:29 PM
 */
package view.gui;

/**
 *
 * @author drew
 */
public class PropertyView extends javax.swing.JPanel {

    /** Creates new form PropertyView */
    public PropertyView() {
        initComponents();
        propDataField.setText("");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        propLabel = new javax.swing.JLabel();
        propDataField = new javax.swing.JTextField();

        propDataField.setText("jTextField1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(propLabel)
                .addGap(18, 18, 18)
                .addComponent(propDataField, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(propLabel)
                    .addComponent(propDataField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField propDataField;
    private javax.swing.JLabel propLabel;
    // End of variables declaration//GEN-END:variables

    public void setLabel(String text) {
        propLabel.setText(text);
    }
    public void setData(String data)
    {
        propDataField.setText(data);
    }
    public String getLabel()
    {
        return propLabel.getText();
    }
    public String getData()
    {
        return propDataField.getText();
    }
    
}