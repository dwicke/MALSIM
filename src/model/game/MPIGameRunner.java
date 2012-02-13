/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

/**
 * need to set the ObjectState of the
 * initial game to the finished game received
 * from mpigameclient
 * set the state to Terminated then the Tournament
 * will know
 * @author drew
 */
public class MPIGameRunner extends ThreadedGameRunner{
    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void pauseGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void resumeGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void terminateGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
