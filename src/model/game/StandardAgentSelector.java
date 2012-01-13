/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.util.ArrayList;
import model.agent.Agent;
import util.ObjectState;

/**
 *
 * @author drew
 */
public class StandardAgentSelector extends AgentSelector{

    @Override
    public boolean hasContestants() {
        
        System.out.println("Number selected = " + getNumSelect() + " Get agents Avail " + getAgents().size());
        if (getNumSelect() > getAgents().size())
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Agent> nextContestants() {
        ArrayList<Agent> next = new ArrayList<Agent>();
        for (int i = 0; i < getAgents().size(); i++)
        {
            if (next.size() < getNumSelect() )
            {
                ObjectState st = getAgents().get(i).getAgentObjectState();
                if (st == null || st.getState() == State.TERMINATED)
                {
                    next.add(getAgents().get(i));
                }
            }
        }
      //  return getAgents();
        return next;
    }
    @Override
    public String toString() {
        return "Standard_Agent_Selector";
    }
}
