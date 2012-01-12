/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import model.agent.Agent;

/**
 * An interface to specify what agents to play.
 * I will need info on the games that are playing
 * i can get this by querying the object state of the agent
 * null or finished if not playing a game and will be 
 * Running if playing a game.
 * so I know what agents are already active
 * so if some subclass wants that info then I have it
 * I also need to know what agents are available
 * to choose from I need to know how many agents
 * are needed for the game in order to select them
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
    public void addAgent(Agent ag)
    {
        this.agents.add(ag);
    }
    public boolean removeAgent(Agent ag)
    {
        return this.agents.remove(ag);
    }
    public ArrayList<Agent> getAgents()
    {
        return agents;
    }
    public int getNumSelect()
    {
        return numSelect;
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
