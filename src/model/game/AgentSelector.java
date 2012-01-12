/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import model.agent.Agent;

/**
 * An interface to specify what agents to play.
 * @author drew
 */
public abstract class AgentSelector {
    private ArrayList<Agent> agents;
    private int numSelect;
    public AgentSelector()
    {
        setup();
    }
    /**
     * Sets the number of agents to select when getting the next 
     * contestants.  Must be less than the number of agents and
     * must be greater than one.  But I leave that up to the user
     * to check (precondition)
     * @param num 
     */
    public void setNumToSelect(int num)
    {
        numSelect = num;
    }
    /**
     * Sets the set of players that the selector can choose from
     * @param agents 
     */
    public void setPlayers(ArrayList<Agent> agents)
    {
        this.agents = agents;
    }
    
    /**
     * Returns whether there are any contestants. true if there are false if not.
     * @return 
     */
    public abstract boolean hasContestants();
    /**
     * This is the method that will return the list of
     * new contestants.  Will return null when no more
     * agents to be selected.
     * @return 
     */
    public abstract ArrayList<Agent> nextContestants();
            
    /**
     * sets up the class's fields
     */
    private void setup()
    {
        agents = new ArrayList<Agent>();
    }
    
}
