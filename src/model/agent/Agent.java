/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agent;

import model.properties.agent.AgentProperties;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.lang.Thread.State;
import util.ObjectState;
import util.Viewable;

/**
 * viewable for the score, name....
 * @author drew
 */
//@XStreamAlias("Agent") not sure I need this since this is abstract
public abstract class Agent extends Viewable implements  Runnable, Comparable<Agent> {
    private AgentProperties prop;
    private double score;
    private String name;
    private int id;
    private ObjectState state;
    
    
    public Agent()// remember that the default const. doesn't get called when made with xstream
    {
        super();
    }
    public void setState(ObjectState newState)
    {
        this.state = newState;
    }
    public ObjectState getAgentObjectState()
    {
        return state;
    }
    /**
     * Sets the properties object for the agent
     * @param prop 
     */
    public void setProperties(AgentProperties prop)
    {
        this.prop = prop;
    }
    /**
     * Returns the properties object for the agent
     * @return 
     */
    public AgentProperties getProperties()
    {
        return prop;
    }
    /**
     * Adds the score to the current score.
     * @param score score to be added.
     */
    public void addScore(double score)
    {
        this.score += score;
    }
    /**
     * Returns the current score of the agent
     * @return 
     */
    public double getScore()
    {
        return score;
    }
    /**
     * Sets the score of the agent to zero.
     */
    public void resetScore()
    {
        score = 0;
    }
    /**
     * Agent takes its turn.
     */
    public abstract void takeTurn();
    
    /**
     * Returns the name of the agent as a string.
     * @return 
     */
    @Override
    public abstract String toString();
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public int getID()
    {
        return id;
    }
    
    public void setID(int newID)
    {
        id = newID;
    }
            
    
}
