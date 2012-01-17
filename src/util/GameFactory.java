/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.game.Game;

/**
 *
 * @author drew
 */
public class GameFactory extends GenericFactory{
    @Override
     public Game getObject(String simpleClassName) {
        Game game = (Game) super.getObject(simpleClassName); 
        game.getGameProps().setString(simpleClassName);
        game.getGameProps().initProperties();
        return game;
     }

    public boolean generateMaping() {
        return super.generateMaping("config/GameList.cfg");
    }
    
    
}
