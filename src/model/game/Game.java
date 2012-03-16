/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.io.Serializable;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.agent.Agent;
import model.properties.game.GameProperties;
import util.ObjectState;
import util.Subscriber;

/**
 *
 * @author drew
 */
public abstract class Game implements Runnable, Comparable<Game>, Subscriber {
    // TODO:
    // I need to use generics to specify the players so that I can ensure that I get the correct
    // type of players in game subclasses otherwise there will be errors if for example
    // they chose a gamut agent and a non-gamut agent when they play the game...
    protected ArrayList<Agent> players;
   
    
    protected GameProperties props;// must set the correct one when subclassing in the constructor
    
    
    protected ObjectState state; 
    
    // the winner of this game ie whoever has the highest reward maybe...
    protected Agent winner;
    
    
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
    @Override
    public void run() {
        startGame();
        terminate();
    }
    @Override
    public int compareTo(Game o) {
        return this.getGameProps().toString().compareTo(o.getGameProps().toString());
    }
    public void terminate()
    {
        for (Agent ag : getAgents())
        {
            ag.cleanupAgent();
            ag.getAgentObjectState().setState(State.TERMINATED);
        }
        System.out.println("Number of agents: " + getAgents().size());
        this.getGameState().setState(State.TERMINATED);
        System.out.println("Finished");
    }
    public boolean checkTerminate()
    {
        
        if (state.getState().compareTo(State.TERMINATED) == 0)
        {
            return true;
        }
        return false;
    }
    
    /**
     * Pauses if necessary 
     */
    public void checkPaused()
    {
        // basiclly take care of pausing this.
        if (state.getState().compareTo(State.WAITING) == 0)
        {
            System.out.println("I am Waiting...................");
            try {
                // then I should pause not sure that this will work
                synchronized(this)
                {
                    // I give up "this" monitor when I call wait so someone else
                    // can call notify
                    this.wait();
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
        }
        
    }
}
