/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

/**
 * Uses the gamefactory to create the game the user has specified
 * Essentially the user sets up a game and then it will be played
 * by the agents that were chosen in a tournament style.
 * 
 * This class provides the tournament model.  I will make another 
 * class that will provide the logic and a factory class.  Therefore,
 * users will be able to provide new tournament logic.
 * 
 * A tournament can be started as a thread. since it implements runnable.
 * 
 * @author drew
 */
public class Tournament implements Runnable {

    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
