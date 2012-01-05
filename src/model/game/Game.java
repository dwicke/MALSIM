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
 * 
 * The purpose of the interface is to specify a generic
 * game.  It extends Runnable so that games can be run
 * in parallel.
 * 
 * I also need the observer/observable from course scheduler
 */
public interface Game extends Runnable {
   
    // has agents that play a game
    public void addAgent(Agent agent); // adds the agent to the set
    public void startGame(); // plays the game and sets the winner
    public Agent getWinner();// returns the agent that won the game
    public ArrayList<Agent> getAgents(); // returns the list of agents playing the game
    public void setGameProperties(GameProperties prop);
}
