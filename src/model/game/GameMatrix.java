/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;
import ma2.*;
/**
 *
 * @author drew
 * 
 * This defines a game matrix given x agents
 * The entries in the matrix correspond to the 
 * payoff of that strategy.
 */
public class GameMatrix {
    // has a PayoffMatrix for each of the players
    private Array gamematrix;
    
    /**
     * Constructor requires that the number of agents
     * and the number of strategies per agent be known
     * in order to create a zeroed matrix for the game.
     * @param strategies 
     * is an array of the number of strategies available to each agent
     */
    public GameMatrix(int strategies[], java.lang.Class dataType)
    {
         gamematrix = Array.factory(dataType, strategies);
    }
    
    
    /**
     * Set the value of a field in the
     * game matrix.
     * @param loc The index of the field to set
     * @param value The value to set at the location
     */
    public void setValue(Index loc, Object value)
    {
        gamematrix.setObject(loc, value);
    }
    /**
     * Gets the value at the location specified
     * in the game matrix.
     * @param loc the index location
     * @return  the value at the location
     */
    public Object getValue(Index loc)
    {
        return gamematrix.getObject(loc);
    }
    
    /**
     * Gets an iterator for the game matrix.
     * @return 
     */
    public IndexIterator getMatrixIter()
    {
        return gamematrix.getIndexIterator();
    }
    
    /**
     * Gets the 1d array version of the matrix
     * @return 
     */
    public Object getArray()
    {
        return gamematrix.copyTo1DJavaArray();
    }
    
    
}
