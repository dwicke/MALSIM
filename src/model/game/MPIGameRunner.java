/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import ibis.mpj.MPJ;
import ibis.mpj.MPJException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ObjectState;

/**
 * need to set the ObjectState of the
 * initial game to the finished game received
 * from mpigameclient
 * set the state to Terminated then the Tournament
 * will know
 * @author drew
 */
public class MPIGameRunner extends ThreadedGameRunner{
    
    private Tag usedTag;
    
    public void setTag(Tag tag)
    {
        usedTag = tag;
    }
    
    @Override
    public void startGame() {
        sendObject(g);
        try {
            while(MPJ.COMM_WORLD.iprobe(usedTag.getTag(), usedTag.getTag()) == null)
            {
                
            }
        } catch (MPJException ex) {
            Logger.getLogger(MPIGameRunner.class.getName()).log(Level.SEVERE, null, ex);
            // I thnk that this means that the probe failed since the source does
            // not exist
        }
        // The game has terminated sucessfully! or there was an exception
        
            
    }

    @Override
    public void pauseGame() {
        ObjectState st = new ObjectState(Thread.State.WAITING);
        sendObject(st);
    }

    @Override
    public void resumeGame() {
        ObjectState st = new ObjectState(Thread.State.RUNNABLE);
        sendObject(st);
    }

    /**
     * tourn calls this to terminate a game.
     */
    @Override
    public void terminateGame() {
        ObjectState st = new ObjectState(Thread.State.TERMINATED);
        sendObject(st);
    }
    
    @Override
    public void shutdownRunner()
    {
        // first shutdown the game
        terminateGame();
        // then shutdown the proc
        terminateGame();
        
    }
    
    private void sendObject(Object st)
    {
        Object[] termState = new Object[1];
        termState[0] = st;
        try {
            
            MPJ.COMM_WORLD.send(termState, 0, 1, MPJ.OBJECT, usedTag.getTag(), usedTag.getTag());
        } catch (MPJException ex) {
            Logger.getLogger(MPIGameRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
