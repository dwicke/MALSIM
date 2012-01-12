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
public class StandardEliminator implements Eliminator{

    @Override
    public Agent eliminate(ArrayList<Agent> agents) {
        return agents.get(0);
    }
    
    @Override
    public String toString() {
        return "Standard_Eliminator";
    }
    
}
