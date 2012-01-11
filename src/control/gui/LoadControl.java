/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import com.thoughtworks.xstream.XStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.game.Batch;
import model.game.Tournament;
import model.properties.game.GameProperties;
import model.properties.game.TournamentProperties;

/**
 *
 * @author drew
 */
public class LoadControl {

    public LoadControl() {
    }

    public Batch load(File file) {
        // load the batch using xstream
        Batch newBatch = null;
        XStream x = new XStream();
        newBatch = (Batch) x.fromXML(file);
        return newBatch;
    }
}
