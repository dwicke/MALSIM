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
    protected int order;// this specifies which set of strategies I have to use.
    
    public MatrixAgent()
    {
        super();
    }
    
    public void setMatrix(MatrixGame gameMatrix)
    {
        matrix = gameMatrix;
    }
    
    public void setOrder(int order)
    {
        this.order = order;
    }
    
    public int getOrder()
    {
        return order;
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
