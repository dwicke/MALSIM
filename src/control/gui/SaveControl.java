/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        XStream x = new XStream();
            FileWriter fstream;
            try {
                fstream = new FileWriter(saveFile);
                BufferedWriter out = new BufferedWriter(fstream);
                x.toXML(toSave, out);
                 } catch (IOException ex) {
                Logger.getLogger(SaveControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        return false;
    }
}
