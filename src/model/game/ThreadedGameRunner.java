/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;

/**
 *
 * @author drew
 */
public class ThreadedGameRunner implements Runnable, GameRunner {

    protected Game g;
    @Override
    public void run() {
        startGame();
    }

    @Override
    public void setGame(Game game) {
        g = game;
    }

    @Override
    public Game getGame() {
        return g;
    }

    @Override
    public void startGame() {
        g.run();
    }

    @Override
    public void pauseGame() {
        g.getGameState().setState(State.WAITING);
    }

    @Override
    public void resumeGame() {
        synchronized(g)// This is right since the game will have called
                // wait and have thus given up its monitor and I can synch on
                // it and then call notify to wake it up
            {
                g.notify();
            }
    }

    @Override
    public void terminateGame() {
        g.getGameState().setState(State.TERMINATED);
    }

    @Override
    public int compareTo(GameRunner t) {
        return this.getGame().compareTo(t.getGame());
    }

    @Override
    public void shutdownRunner() {
        terminateGame();
    }
    
    
}
