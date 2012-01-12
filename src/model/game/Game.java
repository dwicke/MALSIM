/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import model.agent.Agent;
import model.properties.game.GameProperties;
import util.ObjectState;
import util.Subscriber;

/**
 *
 * @author drew
 */
public abstract class Game implements Runnable, Comparable<Game>, Subscriber {
    private ArrayList<Agent> players;
    private GameProperties props;// must set the correct one when subclassing in the constructor
    private ObjectState state; 
    private Agent winner;
    
    
    public Game()
    {
        super();
        setup();
    }
    
    private void setup()
    {
        players = new ArrayList<Agent>();
    }
    /**
     * Adds the agent to the game
     * @param agent the agent that will play the game
     */
    public void addAgent(Agent agent)
    {
        players.add(agent);
    }
    /**
     * Sets all of the agents to the game
     * @param agents 
     */
    public void setAgents(ArrayList<Agent> agents)
    {
        this.players = agents;
    }
    /**
     * Starts to play the game. sets the winner agent and sets the
     * state to finished when done the game.  During the game
     * the state must be check to see if the game should be paused.
     * sets the state of the agents to running set this as the
     * observer
     */
    public abstract void startGame();
    /**
     * Returns the winner of the game.
     * @return 
     */
    public Agent getWinner()
    {
        return winner;
    }
    /**
     * Returns the list of agents that are playing the game
     * @return 
     */
    public ArrayList<Agent> getAgents()
    {
        return players;
    }
    /**
     * Sets the game's properties.
     * @param prop 
     */
    public void setGameProperties(GameProperties prop)
    {
        props = prop;
    }
    public ObjectState getGameState()
    {
        return state;
    }
    /**
     * Returns the game's properties
     * @return 
     */
    public GameProperties getGameProps()
    {
        return props;
    }
    /**
     * Sets the Game's state.
     * @param st 
     */
    public void setObjectState(ObjectState st)
    {
        state = st;
    }
    
}
