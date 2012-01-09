/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drew
 */
public class GenericFactory {

    private String file;
    private TreeMap<String, String> simpToFull;

    public GenericFactory() {
        simpToFull = new TreeMap<String, String>();
    }

    public void generateMaping(String file) {
        FileInputStream fstream = null;
        try {
            // load and read the file
            // each line will be simp full
            fstream = new FileInputStream(file);
            // Get the DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                String names[] = strLine.split(" ");
                if (names.length == 2) {
                    simpToFull.put(names[0], names[1]);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(GenericFactory.class.getName()).log(Level.SEVERE, null, ex);
        }


    }

    /**
     * Returns the set of names of classes.
     * @return 
     */
    public Set<String> getSimpleRepresentation() {
        return simpToFull.keySet();
    }

    /**
     * Gets a new object associated with the simple class name.
     * @param simpleClassName the name of the class to create.
     * @return Null if an object can't be created and the object if it can
     */
    public Object getObject(String simpleClassName) {
        if (simpToFull.containsKey(simpleClassName)) {
            Class newClass = null;
            try {
                newClass = Class.forName(simpToFull.get(simpleClassName));
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GenericFactory.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
            Object newObj = null;
            try {
                newObj = newClass.newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(GenericFactory.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(GenericFactory.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                return newObj;
            }

        }
        return null;
    }
}
