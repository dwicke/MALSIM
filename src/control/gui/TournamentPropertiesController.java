/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import model.properties.game.TournamentProperties;

/**
 *
 * @author drew
 */
public class TournamentPropertiesController {
    private TournamentProperties tournProps;
   
    public TournamentPropertiesController(TournamentProperties prop){
        tournProps = prop;
    }
    public AgentChooserControl getAgentChooser()
    {
        return new AgentChooserControl(tournProps);
    }
    
    public GameChooserControl getGameChooser()
    {
        return new GameChooserControl(tournProps);
    }
    
    public EliminatorChooserControl getElimChooser()
    {
        return new EliminatorChooserControl(tournProps);
    }

    public AgentSelectorChooserControl getAgentSelectorChooser() {
        return new AgentSelectorChooserControl(tournProps);
    }
    
    
     
}
