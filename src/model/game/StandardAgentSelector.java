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
        return false;
    }

    @Override
    public ArrayList<Agent> nextContestants() {
        return null;
    }
    
}
