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
        // can't get and set 
        synchronized(getAgents())
        {
            for (Agent ag : getAgents())
            {
                if (next.size() < getNumSelect() )
                {
                    ObjectState st = ag.getAgentObjectState();
                    if (st == null)
                    System.out.println("null state");
                    // a blocked agent means that it has played and is available to be used
                    if (st == null || st.getState() == State.BLOCKED)
                    {
                        // must set it so it has a state so that it is thread safe
                       // getAgents().get(i).setState(new ObjectState(State.NEW));
                        next.add(ag);
                    }
                }
            }
            if (next.size() < getNumSelect())
            {
//                for (int i = 0; i < next.size(); i++)
//                {
//                        getAgents().get(i).setState(new ObjectState(State.BLOCKED));
//                }
                
            
                next.clear();
                //return next;
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
