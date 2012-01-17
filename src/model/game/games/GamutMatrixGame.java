/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.games;

import java.rmi.RemoteException;
import java.util.ArrayList;
import model.agent.Agent;
import model.agent.MatrixAgent;
import model.properties.game.GamutGameProperties;

/**
 *
 * @author drew
 */
public class GamutMatrixGame extends model.game.MatrixGame {

    public GamutMatrixGame()
    {
        super();
        
        this.setGameProperties(new GamutGameProperties());
        
    }
    
    
    /**
     * Must check that if the game state is set to waiting that
     * I call wait on this.
     */
    @Override
    public void startGame() {
        // generate a matrix and setup the agents to play
        this.setupMatrixGame();
        
      //  ArrayList<Integer> actions = new ArrayList<Integer>();
        int actions[] = new int[getMatrixAgents().size()];
        // give the two agents the 
        for (int i = 0; i < getGameProps().getNumReps(); i++)
        {
            checkPaused();// pause if necessary
            
            // get the actions from each of the agents
            for (MatrixAgent ag : getMatrixAgents())
            {
                ag.takeTurn();
                actions[ag.getOrder()] = ag.getAction();
            }
            
            
            
            
            // now I can assign payoffs to the agents
            for (MatrixAgent ag : getMatrixAgents())
            {
                ag.addScore(getGameProps().getMatrix().getPayoff(actions, ag.getOrder()));
            }
            
        }
    }



    @Override
    public void setAgents(ArrayList<Agent> agents) {
        super.setAgents(agents);
    }
    

    

    @Override
    public void update(Object pub, Object code) throws RemoteException {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
