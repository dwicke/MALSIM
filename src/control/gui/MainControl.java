/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

/**
 * This is the controller for the view
 * it acts as a factory to create lower level controllers
 * It also is the main place that the master ModelControl object resides.
 * such as BatchControl, SaveControl, LoadControl, RunningControl, GraphControl,
 * RunningControl, TutorialControl.  This is also where developers can add
 * their own control creator method so that the MainFrame can access it.
 * @author drew
 */
public class MainControl {
    
    BatchControl batchControl;
    public MainControl()
    {
        batchControl = new BatchControl();
    }
    
    // Need to think about where to put batch
    // I think I will put it in batch
    // and will get it if I need it from the batch control.
    public BatchControl getBatchControl()
    {
        return batchControl;
    }
    public BatchControl getNewBatchControl()
    {
        batchControl = new BatchControl();
        return batchControl;
    }
            
}
