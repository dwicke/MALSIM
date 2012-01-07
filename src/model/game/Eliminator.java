/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import model.agent.Agent;

/**
 *
 * @author drew
 */
public interface Eliminator {
    /**
     * Eliminates an agent from the list.
     * @param agents the list of agents to choose from
     * @return the agent to eliminate.
     */
    abstract Agent elimnate(ArrayList<Agent> agents);
    /**
     * Returns the name of the eliminator.
     * @return 
     */
    abstract String toString();
            
}
