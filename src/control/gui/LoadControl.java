/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.io.File;
import model.game.Batch;

/**
 *
 * @author drew
 */
public class LoadControl {

    
    public LoadControl() {
    }
    
    public MainControl load(File file)
    {
        // load the batch using xstream
        Batch newBatch = null;
        
        return new MainControl(new ModelControl(newBatch));
    }
    
    
}