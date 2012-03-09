/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agent;

import edu.stanford.multiagent.gamer.Game;
import edu.stanford.multiagent.gamer.MatrixGame;

/**
 * These agents require a game matrix in
 * order to take their turn. So essentially
 * any new matrix games must extend MatrixGame from
 * Gamut.
 * @author drew
 */
public abstract class GamutAgent extends Agent{
    protected Game game;
    protected int action;
    protected int order;// this specifies which set of strategies I have to use.
    
    public GamutAgent()
    {
        super();
    }
    
    public void setMatrix(Game gameMatrix)
    {
        game = gameMatrix;
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
     * this gives the agent the actions taken
     * an iteration of a game
     * @param actions 
     */
    public void setJointAction(int actions[])
    {
        // don't need to do anything if don't want to
        // since not all gamut agents need to know
    }
    
    public void setNumAgents(int numAgents)
    {
        // let the overrider take care of this
        // if they need it otherwise it is useless
    }
    
    public void setNumReps(int numReps)
    {
        // maybe i should make a repeat agent super class?
        // rather than keep on making these additions
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
