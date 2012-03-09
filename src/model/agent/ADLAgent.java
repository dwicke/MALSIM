/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agent;

import control.algorithm.ADLAlgorithm;
import control.algorithm.AddaptiveDynamicsLearner;
import model.properties.agent.ADLProps;

/**
 *
 * @author drew
 */
public class ADLAgent extends GamutAgent{

    protected ADLAlgorithm agentAlgorithm;
    protected int numAgents;
    private int numiter;
    
    public ADLAgent() {
        super();
        this.setProperties(new ADLProps());
        
    }

    @Override
    public void setOrder(int order) {
        super.setOrder(order);
    }

    @Override
    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }
    
    

    @Override
    public void takeTurn() {
        if (agentAlgorithm == null)
        {
            agentAlgorithm = new ADLAlgorithm( (ADLProps) this.getProperties(), numAgents, game.getNumActions(order - 1), numiter);
            agentAlgorithm.setOrder(order);
        }
        action = agentAlgorithm.getAction();
        //System.out.println("agent " + this.toString() + " is playing " + action);
    }

    @Override
    public String toString() {
        return "ADL";
    }

    @Override
    public void run() {
        takeTurn();
    }

    @Override
    public void generateViewFields() {
        
    }

    @Override
    public void addScore(double score) {
        super.addScore(score);
        agentAlgorithm.setScore(score);
    }

    @Override
    public void setJointAction(int[] actions) {
        super.setJointAction(actions);
        agentAlgorithm.setJointAction(actions);
    }

    @Override
    public void setNumReps(int numReps) {
        this.numiter = numReps;
    }

    
    
    
    
}
