/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import ibis.mpj.MPJException;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.game.Batch;
import model.game.BatchModel;
import model.game.Tournament;
import model.game.TournamentFactory;
import util.Subscriber;

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
    
    public void addBatchSub(Subscriber sub)
    {
        batch.subBatch(sub);
    }
    
    public void removeBatchSub(Subscriber sub)
    {
        batch.removeBatchSub(sub);
    }
    
    public void addAllTournSubscriber(Subscriber sub)
    {
        batch.subAllTourns(sub);
    }
    
    public void removeAllTournSubscriber(Subscriber sub)
    {
        batch.removeAllTournsSub(sub);
    }
    
    public BatchModel getBatchModel()
    {
        return new BatchModel(batch);
    }
    
    public void startBatch()
    {
       batch.startTournaments();
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
        Tournament tourn = null;
        try {
            tourn = TournamentFactory.getTournament(); //new Tournament();
        } catch (MPJException ex) {
            Logger.getLogger(BatchControl.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public ArrayList<String> getTournNames()
    {
        return batch.getTournNames();
    }
    
    public void pauseTourn(String tournName)
    {
        batch.pauseTourn(tournName);
    }
    public void resumeTourn(String tournName)
    {
        batch.resumeTourn(tournName);
    }
    public void termTourn(String tournName)
    {
        batch.termTourn(tournName);
    }
    
}
