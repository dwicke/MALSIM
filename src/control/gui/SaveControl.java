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
public class SaveControl {
    private Batch toSave;
    public SaveControl(Batch theBatch)
    {
        toSave = theBatch;
    }
    
    public boolean save(File saveFile)
    {
        // Save Batch to the file saveFile
        return false;
    }
}
