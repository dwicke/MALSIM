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
    
    private ModelControl model;
    public MainControl()
    {
        model = new ModelControl();
    }
    
    public MainControl(ModelControl model)
    {
        this.model = model;
    }
    
    
    public SaveControl getSaveControl()
    {
        return new SaveControl(model.getBatch());
    }
    
    public LoadControl getLoadControl()
    {
        return new LoadControl();
    }
    
    public BatchControl getBatchControl()
    {
        return new BatchControl(model.getBatch());
    }
}
