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
import model.agent.MatrixAgent;
import model.agent.QLearningAgent;
import model.game.Game;
import model.properties.game.BattleOfTheSexesProperties;
import model.properties.game.MatrixGameProperties;


/**
 *
 * @author drew
 */
public class BattleOfTheSexesGame extends model.game.MatrixGame {

    public BattleOfTheSexesGame()
    {
        super();
        this.setGameProperties(new BattleOfTheSexesProperties());
    }
    
    
    
    @Override
    public void startGame() {
        // generate a matrix
        //BattleOfTheSexesProperties props = ((MatrixGameProperties)this.getGameProps());
        
        MatrixGame g = this.getGameProps().getMatrix();
        g.doGenerate();
        
        
        for (int i = 0; i < getGameProps().getNumReps(); i++)
        {
            this.getMatrixAgents().get(0).takeTurn();
        }
    }



    @Override
    public void setAgents(ArrayList<Agent> agents) {
        super.setAgents(agents);
    }
    
    

    @Override
    public void run() {
        
        // we assume that the agents that are playing
        // are MatrixAgents
        startGame();
        
        for (Agent ag : getAgents())
        {
            ag.getAgentObjectState().setState(State.TERMINATED);
        }
        System.out.println("Number of agents: " + getAgents().size());
        this.getGameState().setState(State.TERMINATED);
        System.out.println("Finished");
    }

    @Override
    public int compareTo(Game o) {
        return this.getGameProps().toString().compareTo(o.getGameProps().toString());
    }

    @Override
    public void update(Object pub, Object code) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
}
