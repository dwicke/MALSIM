/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import model.game.MatrixGenerator;

/**
 *
 * @author drew
 */
public class MatrixGameProperties extends GameProperties{

    private int numAgents, numStrats, numReps;
    private MatrixGenerator matrixGen;
    
    public MatrixGameProperties()
    {
        super();
    }
    
    @Override
    public String toString() {
        return "Matrix Game";
    }

    @Override
    public void generateViewFields() {
        this.setField("number of agents", numAgents);
        this.setFieldClass("number of agents", Integer.class);
        
        this.setField("number of strategies", numStrats);
        this.setFieldClass("number of strategies", Integer.class);
        
        this.setField("number of game repitions", numReps);
        this.setFieldClass("number of game repitions", Integer.class);
        
    }
    
}
