/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.util.ArrayList;
import model.agent.Agent;

/**
 *
 * @author drew
 */
public class StandardEliminator implements Eliminator{

    @Override
    public Agent eliminate(ArrayList<Agent> agents) {
        for (Agent ag : agents)
        {
            if (ag.getAgentObjectState() != null )//&& ag.getAgentObjectState().getState() == State.TERMINATED)
            {
                return ag;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "Standard_Eliminator";
    }
    
}
