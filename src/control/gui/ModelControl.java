/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import model.game.Batch;

/**
 * This class stores the model objects.
 * @author drew
 */
public class ModelControl {
    private Batch curBatch;
    public ModelControl()
    {
        curBatch = new Batch();
    }

    ModelControl(Batch newBatch) {
        curBatch = newBatch;
    }
    
    public Batch getBatch()
    {
        return curBatch;
    }
    
    public void setBatch(Batch newBatch)
    {
        curBatch = newBatch;
    }
}
