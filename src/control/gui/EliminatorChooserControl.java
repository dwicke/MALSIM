/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import model.game.Eliminator;
import model.properties.game.TournamentProperties;

/**
 *
 * @author drew
 */
public class EliminatorChooserControl {
    private TournamentProperties tournProps;
    public EliminatorChooserControl(TournamentProperties tournProps)
    {
        this.tournProps = tournProps;
    }
    
    public void setEliminator(Eliminator elim)
    {
        this.tournProps.setEliminatorMethod(elim);
    }
    
    public Eliminator getEliminator()
    {
        return this.tournProps.getEliminator();
    }
            
}
