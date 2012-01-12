/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
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
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        this.getGameState().setState(State.TERMINATED);
    }

    @Override
    public int compareTo(Game o) {
        return this.getGameProps().toString().compareTo(o.getGameProps().toString());
    }
    
}
