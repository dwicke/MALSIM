/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import edu.stanford.multiagent.gamer.BattleOfTheSexes;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author drew
 */
public class BattleOfTheSexesProperties extends MatrixGameProperties{

    //private BattleOfTheSexes game;

    public BattleOfTheSexesProperties() {
        super();
        try {
            game = new BattleOfTheSexes();
        } catch (Exception ex) {
            Logger.getLogger(BattleOfTheSexesProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    @Override
    public String toString() {
        return game.getName();
    }
    
}
