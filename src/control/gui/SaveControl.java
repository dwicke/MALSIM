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
    private Batch toSave = null;
    public SaveControl(Batch theBatch)
    {
        toSave = theBatch;
    }
    
    /**
     * Save using xstream
     * @param saveFile The file to save batch to
     * @return return whether successful
     */
    public boolean save(File saveFile)
    {
        // Save Batch to the file saveFile
        return false;
    }
}
