/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.io.File;
import java.util.ArrayList;
import model.game.Batch;
import model.game.Tournament;

/**
 *
 * @author drew
 */
public class BatchControl {
    private Batch batch;
    public BatchControl()
    {
        batch = new Batch();
        
    }
    
    public SaveControl getSaveControl()
    {
        return new SaveControl(batch);
    }
    
    public Batch getLoadControl(File file)
    {
        batch = (new LoadControl()).load(file);
        return batch;
    }
    
    public void setBatch(Batch batch)
    {
        this.batch = batch;
        
    }
    
    public TournamentControl getNewTournControl(String name)
    {
        Tournament tourn = new Tournament();
        tourn.setString(name);
        batch.addTournament(tourn);
        return new TournamentControl(tourn);
    }
    
    public ArrayList<TournamentControl> getTournControls()
    {
        ArrayList<TournamentControl> controls = new ArrayList<TournamentControl>();
        for (Tournament tourn : batch.getTourn())
        {
            controls.add(new TournamentControl(tourn));
        }
        return controls;
    }
    
    public void removeTourn(Tournament tourn)
    {
        batch.removeTournament(tourn);
    }
}
