/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PropertiesChooserView.java
 *
 * Created on Jan 8, 2012, 4:23:02 PM
 */
package view.gui;

import control.gui.ChooserControl;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.DefaultListModel;

/**
 *
 * @author drew
 */
public class PropertiesChooserView extends javax.swing.JPanel {

    private ChooserControl controller = null;
    private DefaultListModel choicesModel, chosenModel = null;

    /** Creates new form PropertiesChooserView */
    public PropertiesChooserView() {
        initComponents();
        choicesModel = new DefaultListModel();
        chosenModel = new DefaultListModel();
        chooseList.setModel(choicesModel);
        chosenList.setModel(chosenModel);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chooseList = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        chosenList = new javax.swing.JList();
        removeChosenBt = new javax.swing.JButton();
        addChosen = new javax.swing.JButton();
        propTitle = new javax.swing.JLabel();
        chooseLabel = new javax.swing.JLabel();
        chosenLabel = new javax.swing.JLabel();

        chooseList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        chooseList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(chooseList);

        chosenList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        chosenList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        chosenList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                chosenListValueChanged(evt);
            }
        });
        chosenList.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                chosenListFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(chosenList);

        removeChosenBt.setText("<");
        removeChosenBt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                removeChosenBtMousePressed(evt);
            }
        });

        addChosen.setText(">");
        addChosen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                addChosenMousePressed(evt);
            }
        });

        chooseLabel.setText("Choose:");

        chosenLabel.setText("Chosen:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(propTitle)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(removeChosenBt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(addChosen, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chooseLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                .addComponent(chosenLabel)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(propTitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(chooseLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(chosenLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addChosen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removeChosenBt))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addChosenMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addChosenMousePressed
        // TODO add your handling code here:
        if (chooseList.getSelectedIndex() != -1) {
            String chooseString = (String) chooseList.getSelectedValue();
            int selectedIndex = chooseList.getSelectedIndex();
            String chosenString = controller.addChoice(chooseString);
            chosenModel.clear();
            for (String name : controller.getChosen())
                chosenModel.addElement(name);
        }
    }//GEN-LAST:event_addChosenMousePressed

    private void removeChosenBtMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removeChosenBtMousePressed
        // TODO add your handling code here:
        if (chosenList.getSelectedIndex() != -1) {
            String chosenString = (String) chosenList.getSelectedValue();
            int selectedIndex = chosenList.getSelectedIndex();
            //chosenModel.remove(selectedIndex);
            controller.removeChoice(chosenString);
            chosenModel.clear();
            for (String name : controller.getChosen())
                chosenModel.addElement(name);
        }
    }//GEN-LAST:event_removeChosenBtMousePressed

    private void chosenListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_chosenListValueChanged
        // TODO add your handling code here:
        int index = evt.getLastIndex();
        // notify the observers so that they know that i want to see the properties
        // ie the PropertiesView
        if (index < chosenModel.getSize())
        controller.notifyObservers((String)chosenModel.get(index));
    }//GEN-LAST:event_chosenListValueChanged

    private void chosenListFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_chosenListFocusLost
        // TODO add your handling code here:
        chosenList.clearSelection();
    }//GEN-LAST:event_chosenListFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addChosen;
    private javax.swing.JLabel chooseLabel;
    private javax.swing.JList chooseList;
    private javax.swing.JLabel chosenLabel;
    private javax.swing.JList chosenList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel propTitle;
    private javax.swing.JButton removeChosenBt;
    // End of variables declaration//GEN-END:variables

    public void setController(ChooserControl control) {
        controller = control;
        propTitle.setText(controller.toString());
        ArrayList<String> choices = controller.getChoices();
        if (choices != null) {
            for (String val : choices) {
                choicesModel.addElement(val);
            }
        }

        ArrayList<String> chosen = controller.getChosen();
        if (chosen != null) {
            for (String val : chosen) {
                chosenModel.addElement(val);
            }

        }
    }
}
