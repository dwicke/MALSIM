/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import model.game.MatrixGenerator;

/**
 * subclass this to the different types of matrix games
 * like general sum zero sum etc
 * @author drew
 */
public class MatrixGameProperties extends GameProperties {

    private int numAgents, numStrats, numReps;
    private MatrixGenerator matrixGen;

    public MatrixGameProperties() {
        super();
    }

    @Override
    public String toString() {
        return "MatrixGame";
    }

    @Override
    public boolean setField(String fieldAlias, Object val) {
        
        if (fieldAlias.equals("number of agents")) {
            int prev = numAgents;
            try {
                numAgents = Integer.parseInt(val.toString());
            } catch (NumberFormatException exp) {
                numAgents = prev;
                return false;

            }
        } else if (fieldAlias.equals("number of strategies")) {
            int prev = numStrats;
            try {
                numStrats = Integer.parseInt(val.toString());
            } catch (NumberFormatException exp) {
                numStrats = prev;
                return false;

            }
        } else if (fieldAlias.equals("number of game repititions")) {
            int prev = numReps;
            try {
                numReps = Integer.parseInt(val.toString());
            } catch (NumberFormatException exp) {
                numReps = prev;
                return false;

            }
        }
        super.setField(fieldAlias, val);
        return true;
    }

    @Override
    public void generateViewFields() {
        this.setField("number of agents", numAgents);
        this.setFieldClass("number of agents", Integer.class);

        this.setField("number of strategies", numStrats);
        this.setFieldClass("number of strategies", Integer.class);

        this.setField("number of game repititions", numReps);
        this.setFieldClass("number of game repititions", Integer.class);

    }
}
