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
public class StandardAgentSelector extends AgentSelector{

    @Override
    public boolean hasContestants() {
        if (getNumSelect() > getAgents().size())
        {
            return false;
        }
        return true;
    }

    @Override
    public ArrayList<Agent> nextContestants() {
        return getAgents();
    }
    @Override
    public String toString() {
        return "Standard_Agent_Selector";
    }
}
