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
 * uses the combination formula
 * C(n,r) = n!/(r!(n-r)!) to get the number of combinations
 * 
 * essentially I have a list of agents to choose from however,
 * only those that have a state set to BLOCKED or is null
 * can I choose from and the agent list can change.  However,
 * this does not need to be thread safe since I will not remove 
 * and query at the same time.
 * @author drew
 */
public class StandardAgentSelector extends AgentSelector{

    
    
    
    @Override
    public boolean hasContestants() {
        
     //   System.out.println("Number selected = " + getNumSelect() + " Get agents Avail " + getAgents().size());
        if (/*nextContestants().size() == 0 ||*/ getNumSelect() > getAgents().size())
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
                if (st == null)
                System.out.println("null state");
                if (st == null || st.getState() == State.BLOCKED)
                {
                    next.add(getAgents().get(i));
                }
            }
        }
        if (next.size() < getNumSelect())
        {
            next.clear();
            //return next;
        }
      //  return getAgents();
        return next;
    }
    @Override
    public String toString() {
        return "Standard_Agent_Selector";
    }
}
