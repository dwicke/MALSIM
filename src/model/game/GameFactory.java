/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.util.ArrayList;

/**
 * The purpose of the class is to provide a way to
 * create subclasses of Game as well as provide a way
 * for new game classes to be added at runtime.
 * 
 * Not all games must be matrix games.  Only need the
 * agents that are playing the game to have the info they need
 * to play the game and the game will run.
 *
 * 
 * @author drew
 */
public class GameFactory {
    
    
    public ArrayList<String> getListOfGames()
    {
        return null;
    }
}
