/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import edu.stanford.multiagent.gamer.Game;

/**
 * subclass this to the different types of matrix games
 * like general sum zero sum etc
 * @author drew
 */
public abstract class RepeatedGameProperties extends GameProperties {

    private int numReps;
    // private MatrixGenerator matrixGen;
    

    public RepeatedGameProperties() {
        super();
    }

    @Override
    public boolean setField(String fieldAlias, Object val) {

        
        if (fieldAlias.equals("number of game repititions")) {
            System.out.println("Set the num Reps");
            int prev = numReps;
            try {
                numReps = Integer.parseInt(val.toString());
                this.fieldVals.put(fieldAlias, numReps);
            } catch (NumberFormatException exp) {
                numReps = prev;
                return false;
            }

            return true;
        }

        return super.setField(fieldAlias, val);
    }



    @Override
    public void generateViewFields() {
        super.generateViewFields();

        //System.out.println("Generating views");
        //this.setField("number of strategies", numStrats);
        //this.setFieldClass("number of strategies", Integer.class);

        this.fieldVals.put("number of game repititions", numReps);
        this.fieldAlias.put("number of game repititions", Integer.class);

    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }
    
}
