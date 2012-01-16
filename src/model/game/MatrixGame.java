/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.rmi.RemoteException;
import java.util.ArrayList;
import model.agent.Agent;
import model.agent.MatrixAgent;
import model.properties.game.MatrixGameProperties;

/**
 *
 * @author drew
 */
public abstract class MatrixGame extends Game{

    public MatrixGame() {
        super();
        
    }
    
    public void setupMatrixGame()
    {
        edu.stanford.multiagent.gamer.MatrixGame g = this.getGameProps().getMatrix();
        g.doGenerate();
        int order = 1;
        for (MatrixAgent ag : this.getMatrixAgents())
        {
            ag.setMatrix(g);
            ag.setOrder(order);
            order++;
        }
    }
    
    @Override
    public MatrixGameProperties getGameProps()
    {
        return (MatrixGameProperties)props;
    }
    
    public ArrayList<MatrixAgent> getMatrixAgents() throws ClassCastException
    {
        ArrayList<MatrixAgent> matrixAgents = new ArrayList<MatrixAgent>();
        for (Agent ag : players)
        {
            if (ag instanceof MatrixAgent)
            {
                matrixAgents.add((MatrixAgent)ag);
            }
            else
            {
                throw new ClassCastException("Agent was not a MatrixAgent.");
            }
        }
        return matrixAgents;
    }
    
}
