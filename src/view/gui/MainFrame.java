/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainFrame.java
 *
 * Created on Jan 7, 2012, 6:34:23 PM
 */
package view.gui;

import com.thoughtworks.xstream.XStream;
import control.gui.MainControl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 * Will need to start working on the batch view
 * eventually will want to internationalize
 * @author drew
 */
public class MainFrame extends javax.swing.JFrame {

    private MainControl mainControl;

    /** Creates new form MainFrame */
    public MainFrame() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        newBatchMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        loadMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        batchMenuItem = new javax.swing.JMenuItem();
        viewMenu = new javax.swing.JMenu();
        runningMenuItem = new javax.swing.JMenuItem();
        graphMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        tutorialMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setText("File");

        newBatchMenuItem.setText("New Batch");
        newBatchMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                newBatchMenuItemMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newBatchMenuItemMouseClicked(evt);
            }
        });
        fileMenu.add(newBatchMenuItem);

        saveMenuItem.setText("Save");
        saveMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                saveMenuItemMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveMenuItemMouseClicked(evt);
            }
        });
        fileMenu.add(saveMenuItem);

        loadMenuItem.setText("Load");
        loadMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loadMenuItemMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadMenuItemMouseClicked(evt);
            }
        });
        fileMenu.add(loadMenuItem);

        menuBar.add(fileMenu);

        editMenu.setText("Edit");

        batchMenuItem.setText("Batch");
        batchMenuItem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                batchMenuItemMousePressed(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                batchMenuItemMouseClicked(evt);
            }
        });
        editMenu.add(batchMenuItem);

        menuBar.add(editMenu);

        viewMenu.setText("View");

        runningMenuItem.setText("Running");
        viewMenu.add(runningMenuItem);

        graphMenuItem.setText("Graph");
        viewMenu.add(graphMenuItem);

        menuBar.add(viewMenu);

        helpMenu.setText("Help");

        tutorialMenuItem.setText("Tutorial");
        helpMenu.add(tutorialMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 624, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 374, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newBatchMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newBatchMenuItemMouseClicked
       
        newBatchFrame(true);
    }//GEN-LAST:event_newBatchMenuItemMouseClicked

    private void saveMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMenuItemMouseClicked
        // TODO add your handling code here:
        if (mainControl != null) {
            // this means that a batch was created so save it
            // first display the save dialog to choose a folder to save the
            // files to
            // then call get BatchControl on the main control then call
            // get save control and provide it the file to the control 
            // and then it will be saved
            JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            System.out.println("Saving");
                mainControl.getBatchControl().getSaveControl().save(file);
        }
  
  
        } else {
            // nothing to save say so
        }
    }//GEN-LAST:event_saveMenuItemMouseClicked

    private void loadMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadMenuItemMouseClicked
        // TODO add your handling code here:
        overwriteControl();
        // then show the load dialog
        initControl();
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            mainControl.getBatchControl().setBatch(mainControl.getBatchControl().getLoadControl(file));
            //This is where a real application would open the file.

        } else {
            // do nothing
        }
        
        // call the get batch control
        // call get load control from batch control
        // giveing the filename to the control
        // then you have loaded the batch
        // then display whatever...

    }//GEN-LAST:event_loadMenuItemMouseClicked

    /**
     * This is the event that will show the batch properties.
     * @param evt 
     */
    private void batchMenuItemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batchMenuItemMouseClicked
        // TODO add your handling code here:
        if (mainControl == null) {
            // show message saying that no batch
            // ask if want to make a new one
            // if so
            initControl();
        }
        // don't need to pause the running tourns
        // since the properties can't be changed once the 
        // batch has started.
        loadBatchFrame(false);
    }//GEN-LAST:event_batchMenuItemMouseClicked

    private void newBatchMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newBatchMenuItemMousePressed
        // TODO add your handling code here:
        newBatchMenuItemMouseClicked(evt);
    }//GEN-LAST:event_newBatchMenuItemMousePressed

    private void saveMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveMenuItemMousePressed
        // TODO add your handling code here:
        saveMenuItemMouseClicked(evt);
    }//GEN-LAST:event_saveMenuItemMousePressed

    private void loadMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadMenuItemMousePressed
        // TODO add your handling code here:
        loadMenuItemMouseClicked(evt);
    }//GEN-LAST:event_loadMenuItemMousePressed

    private void batchMenuItemMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_batchMenuItemMousePressed
        // TODO add your handling code here:
        batchMenuItemMouseClicked(evt);
    }//GEN-LAST:event_batchMenuItemMousePressed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem batchMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenuItem graphMenuItem;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuItem loadMenuItem;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem newBatchMenuItem;
    private javax.swing.JMenuItem runningMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JMenuItem tutorialMenuItem;
    private javax.swing.JMenu viewMenu;
    // End of variables declaration//GEN-END:variables

    private void initControl() {
        mainControl = new MainControl();
    }

    /**
     * This will provide control to see if the mainControl
     * has been created. and will warn users and stop
     * all running threads in the mainControl.
     */
    private void overwriteControl() {
        if (mainControl != null) {
            // show a warning that you are going to overwrite the current batch
            // if the user says ok
            // then stop all running tournaments/threads
            // can just force stop them since don't care they will be
            // garbage collected
        }
    }

    /**
     * This will load the Batch Frame with the current batch controller
     * and will just bring the current BatchView to the front if there
     * is one already open.
     * @param editable 
     */
    private void loadBatchFrame(Boolean editable) {
        // then display the batch frame providing it the batch controller
        // put 
        // eventually think of a better way to get the batchControl to the new frame
        // not the best way to do it...
        if (BatchView.view != null) {
            BatchView.view.toFront();
        } else {
            BatchView.controller = mainControl.getBatchControl();
            BatchView.isEditable = editable;
            BatchView.main(null);
        }
    }

    /**
     * this will create a new BatchFrame with a new BatchControll
     * unless there is a batch view already created in which case
     * that frame will be brought to the front.
     * @param editable 
     */
    private void newBatchFrame(Boolean editable) {
        if (BatchView.view != null)
        {
            // can't create a new Batch when a batch window is open
            BatchView.view.toFront();
        }
        else
        {
            // first intitilize the control so that I have a controller
            initControl();
            BatchView.controller = mainControl.getNewBatchControl();
            BatchView.isEditable = editable;
            BatchView.main(null);
        }
        
    }
}
