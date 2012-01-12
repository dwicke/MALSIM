/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import java.util.ArrayList;
import model.agent.Agent;
import model.game.AgentSelector;
import model.game.Eliminator;
import model.game.StandardAgentSelector;
import model.game.StandardEliminator;
import model.properties.Properties;
import model.properties.Type;

/**
 *
 * @author drew
 */
public class TournamentProperties extends Properties{

    private ArrayList<Agent> agents;
    private GameProperties gameProps;
    private Eliminator elim;
    private AgentSelector selector;
    private int maxThreads;
    
    public TournamentProperties()
    {
        super();
        setup();
    }
    
    private void setup()
    {
        agents = new ArrayList<Agent>();
        maxThreads = 2;
        elim = new StandardEliminator();
        selector = new StandardAgentSelector();
    }
    
    /**
     * This sets the list of agents that will play in the tournament.
     * @param agents 
     */
    public void setAgents(ArrayList<Agent> agents)
    {
        this.agents = agents;
    }
    
    /**
     * This will add the agent to the list of agents that will play in the tournament
     * @param agent 
     */
    public void addAgent(Agent agent)
    {
        agents.add(agent);
    }
    /**
     * Removes the agent passed from the tournament.
     * @param agent 
     */
    public void removeAgent(Agent agent)
    {
        agents.remove(agent);
    }
    /**
     * This will return the list of agents that will play in the tournament.
     * @return 
     */
    public ArrayList<Agent> getAgents()
    {
        return agents;
    }
    /**
     * Sets the properties for the game that will be used for the tournament.
     * @param props 
     */
    public void setGameProps(GameProperties props)
    {
        this.gameProps = props;
    }
    /**
     * Returns the game properties of the tournament.
     * @return 
     */
    public GameProperties getGameProps()
    {
        return gameProps;
    }
    
    /**
     * Sets the eliminator for the tournament.
     * @param elim 
     */
    public void setEliminatorMethod(Eliminator elim)
    {
        this.elim = elim;
    }
    
    /**
     * Returns the eliminator for the tournament.
     * @return 
     */
    public Eliminator getEliminator()
    {
        return elim;
    }
   
    @Override
    public Type getPropertyType() {
        return Type.Tournament;
    }

    /**
     * Sets the agent selector for the tournament. The functor that will
     * choose what agents to play in a game.
     * @param sel 
     */
    public void setAgentSelector(AgentSelector sel)
    {
        selector = sel;
    }
    
    /**
     * Returns the selector for the tournament
     * @return 
     */
    public AgentSelector getAgentSelector()
    {
        return selector;
    }
    
    /**
     * This sets the maximum number of game threads that will be created by the tournament.
     * @param num 
     */
    public void setMaxNumThreads(int num)
    {
        maxThreads = num;
    }
    /**
     * Returns the max number of game threads 
     * @return 
     */
    public int getNumMaxThreads()
    {
        return maxThreads;
    }
    
    @Override
    public void generateViewFields() {
        this.setField("max threads", maxThreads);
        this.setFieldClass("max threads", Integer.class);
        
        
    }
    
}
