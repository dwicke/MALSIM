/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

/**
 *
 * @author drew
 */
public interface GameRunner extends Runnable, Comparable<GameRunner> {
    
    public void setGame(Game game);
    public Game getGame();
    public void startGame();
    public void pauseGame();
    public void resumeGame();
    public void terminateGame();
    
    
}
