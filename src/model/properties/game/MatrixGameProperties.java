/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import edu.stanford.multiagent.gamer.MatrixGame;
import model.game.MatrixGenerator;

/**
 * subclass this to the different types of matrix games
 * like general sum zero sum etc
 * @author drew
 */
public abstract class MatrixGameProperties extends GameProperties {

    private int /*numStrats,*/ numReps;
   // private MatrixGenerator matrixGen;
    protected MatrixGame game;

    public MatrixGameProperties() {
        super();
    }
/*
    @Override
    public String toString() {
        return "MatrixGame";
    }
*/
    @Override
    public boolean setField(String fieldAlias, Object val) {
        
/*
        if (fieldAlias.equals("number of strategies")) {
            int prev = numStrats;
            try {
                numStrats = Integer.parseInt(val.toString());
                
            } catch (NumberFormatException exp) {
                numStrats = prev;
                return false;
            }
            super.setField(fieldAlias, val);
            return true;
        } else */if (fieldAlias.equals("number of game repititions")) {
            int prev = numReps;
            try {
                numReps = Integer.parseInt(val.toString());
            } catch (NumberFormatException exp) {
                numReps = prev;
                return false;
            }
            super.setField(fieldAlias, val);
            return true;
        }
        
        return super.setField(fieldAlias, val);
    }
    
    public MatrixGame getMatrix()
    {
        return game;
    }
    public void setMatrixGame(MatrixGame game)
    {
        this.game = game;
    }

    @Override
    public void generateViewFields() {
        super.generateViewFields();

        //System.out.println("Generating views");
        //this.setField("number of strategies", numStrats);
        //this.setFieldClass("number of strategies", Integer.class);

        this.setField("number of game repititions", numReps);
        this.setFieldClass("number of game repititions", Integer.class);

    }

    public int getNumReps() {
        return numReps;
    }

    public void setNumReps(int numReps) {
        this.numReps = numReps;
    }
/*
    public int getNumStrats() {
        return numStrats;
    }

    public void setNumStrats(int numStrats) {
        this.numStrats = numStrats;
    }
     * 
     */
}
