/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agent;

import edu.stanford.multiagent.gamer.MatrixGame;

/**
 * These agents require a game matrix in
 * order to take their turn. So essentially
 * any new matrix games must extend MatrixGame from
 * Gamut.
 * @author drew
 */
public abstract class MatrixAgent extends Agent{
    protected MatrixGame matrix;
    protected int action;
    
    public MatrixAgent()
    {
        super();
    }
    
    public void setMatrix(MatrixGame gameMatrix)
    {
        matrix = gameMatrix;
    }
    
    /**
     * returns the action of the agent
     * based on taketurn
     * @return 
     */
    public int getAction()
    {
        return action;
    }
}
