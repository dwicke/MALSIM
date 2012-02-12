/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.rmi.RemoteException;
import model.properties.game.RepeatedGameProperties;

/**
 *
 * @author drew
 */
public abstract class RepeatedGame extends Game{

    @Override
    public RepeatedGameProperties getGameProps()
    {
        return (RepeatedGameProperties)props;
    }
    
    /**
     * Starts the game so that I will ensure that future extensions will always
     * check for the game paused or terminated before each iteration.
     */
    @Override
    public void startGame() {
        for (int i = 0; i < getGameProps().getNumReps(); i++)
        {
            checkPaused();// pause if necessary
            // must check if terminated
            if (checkTerminate() == true)
            {
                // break the for loop
                break;
            }
            repeatedAction();
        }
    }

    /**
     * This is the action that is taken inside the
     * startGame.  So this should represent one
     * game so in the start game loop the next game
     * will be played.
     */
    protected abstract void repeatedAction();
    
}
