/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import model.agent.Agent;
import model.properties.game.GameProperties;
import util.ObjectState;

/**
 *
 * @author drew
 */
public abstract class Game implements Runnable{
    private ArrayList<Agent> players;
    private GameProperties props;
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
     * Starts to play the game. sets the winner agent and sets the
     * state to finished when done the game.  During the game
     * the state must be check to see if the game should be paused.
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
