/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.rmi.RemoteException;
import model.properties.game.MatrixGameProperties;

/**
 *
 * @author drew
 */
public class MatrixGame extends Game{

    public MatrixGame() {
        super();
        setGameProperties(new MatrixGameProperties());
        
    }
    
    

    
    @Override
    public void startGame() {
        for (int i = 0; i < ((MatrixGameProperties)getGameProps()).getNumReps(); i++)
        {
            // tell agents that
            System.out.println("Rep" + i);
        }
    }

    @Override
    public void run() {
        startGame();
        this.getGameState().setState(State.TERMINATED);
    }

    @Override
    public int compareTo(Game o) {
        return this.getGameProps().toString().compareTo(o.getGameProps().toString());
    }

    /**
     * Get notified when the state of the agent changes
     * @param pub
     * @param code
     * @throws RemoteException 
     */
    @Override
    public void update(Object pub, Object code) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
