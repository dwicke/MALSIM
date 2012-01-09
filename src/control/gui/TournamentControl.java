/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import model.game.Tournament;

/**
 *
 * @author drew
 */
public class TournamentControl {
    private Tournament tourn;
    public TournamentControl(Tournament tourn)
    {
        this.tourn = tourn;
        
    }
    
    public Tournament getTournament()
    {
        return tourn;
    }
}
