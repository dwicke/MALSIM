/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.games;

import edu.stanford.multiagent.gamer.MatrixGame;
import java.lang.Thread.State;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.agent.Agent;
import model.agent.GamutAgent;
import model.agent.QLearningAgent;
import model.game.Game;
import model.properties.game.BattleOfTheSexesProperties;
import model.properties.game.RepeatedGameProperties;


/**
 *
 * @author drew
 */
public class BattleOfTheSexesGame extends model.game.games.GamutGame {

    public BattleOfTheSexesGame()
    {
        super();
        this.setGameProperties(new BattleOfTheSexesProperties());
    }
    
    
    /**
     * Must check that if the game state is set to waiting that
     * I call wait on this.
     */
    @Override
    public void startGame() {
        // generate a matrix and setup the agents to play
        this.setupGamutGame();
        
      //  ArrayList<Integer> actions = new ArrayList<Integer>();
        int actions[] = new int[getMatrixAgents().size()];
        // give the two agents the 
        for (int i = 0; i < getGameProps().getNumReps(); i++)
        {
            checkPaused();// pause if necessary
            
            // get the actions from each of the agents
            for (GamutAgent ag : getMatrixAgents())
            {
                ag.takeTurn();
                actions[ag.getOrder()] = ag.getAction();
            }
            
            
            
            
            // now I can assign payoffs to the agents
            for (GamutAgent ag : getMatrixAgents())
            {
                ag.addScore(getGameProps().getGame().getPayoff(actions, ag.getOrder()));
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
