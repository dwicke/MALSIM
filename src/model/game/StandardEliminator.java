/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;
import model.agent.Agent;

/**
 *
 * @author drew
 */
public class StandardEliminator implements Eliminator{

    @Override
    public Agent eliminate(List<Agent> agents) {
        // Assuming that agents is a thread safe list
        // must sync on it as per http://docs.oracle.com/javase/tutorial/collections/implementations/wrapper.html
        synchronized(agents)
        {
            Agent leastAgent = agents.get(0);
            for (Agent ag : agents)
            {
                System.out.println("agent " + ag.toString() + " score " + ag.getScore());
                if (ag.getAgentObjectState() != null && ag.getAgentObjectState().getState() == State.TERMINATED  && ag.getScore() < leastAgent.getScore() )//&& ag.getAgentObjectState().getState() == State.TERMINATED)
                {
                    leastAgent = ag;
                    //return ag;
                }
            }
            System.out.println("the least agent is " + leastAgent.toString());
            return leastAgent;
        }
       // return null;
    }
    
    @Override
    public String toString() {
        return "Standard_Eliminator";
    }
    
}
