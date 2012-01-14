/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import model.game.Batch;
import model.game.Tournament;
import model.properties.game.TournamentProperties;

/**
 * The purpose of this controller is to extract
 * status info from the Model in order for it to 
 * be displayed.
 * @author drew
 */
public class StatusControl {
    Tournament tourn;
    TournamentProperties tournProps;
    public StatusControl(Tournament tourn)
    {
        this.tourn = tourn;
    }
    
    // need to be able to get the status of the given tournament
    
}
