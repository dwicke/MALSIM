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
    
    protected int currentStep = 0;
    protected int numAgents;
    protected int numiter;
    protected int curGameid = 0;
    
    
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

    @Override
    public void addScore(double score) {
        super.addScore(score);
        if (currentStep == 0 || numiter == currentStep)
        {
            currentStep = 0;
            curGameid++;
            stats.addTrace("reward" + curGameid, numiter);
        }
        
        stats.addPoint("reward" + curGameid, currentStep, score);
        currentStep++;
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
    
    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }
    
    public void setNumReps(int numReps) {
        this.numiter = numReps;
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
