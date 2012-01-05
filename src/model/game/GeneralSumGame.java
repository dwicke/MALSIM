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
public class GeneralSumGame implements Game{

    private ArrayList<Agent> players;
    private GameProperties props;
    
    
    public GeneralSumGame()
    {
        players = new ArrayList<Agent>();
    }
    
    @Override
    public void addAgent(Agent agent) {
        players.add(agent);
    }

    /**
     * Should notify observers that the game is finished.
     */
    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Agent getWinner() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Agent> getAgents() {
        return players;
    }

    @Override
    public void setGameProperties(GameProperties prop) {
        props = prop;
    }

    /**
     * Starts the game in a new thread.  I will need to 
     * also make this observable/observer type thing
     * so that tournament will know when the game is done.
     */
    @Override
    public void run() {
        startGame();
    }
    
}
