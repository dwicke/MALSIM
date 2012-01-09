/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import util.ObjectState;
import util.Subscriber;

/**
 * This class is used to model all tournaments that will be played
 * @author drew
 */
public class Batch implements Subscriber{

    private TreeMap<Tournament, ObjectState> batch;
    
    public Batch()
    {
        batch = new TreeMap<Tournament, ObjectState>();
    }
    
    /**
     * Adds the tournament to the batch and sets the state to NEW.
     * @param tourn Tournament to be added.
     */
    public void addTournament(Tournament tourn)
    {
        ObjectState st = new ObjectState(State.NEW, this);
        
        batch.put(tourn, st);
    }
    
    /**
     * Removes the tournament form the batch.
     * @param tourn the tournament to be removed.
     */
    public void removeTournament(Tournament tourn)
    {
        batch.remove(tourn);
    }
    /**
     * Returns the batch of tournaments
     * @return 
     */
    public TreeMap<Tournament, ObjectState> getBatch()
    {
        return batch;
    }
    
    public Set<Tournament> getTourn()
    {
        return batch.keySet();
    }
    /**
     * This will be used to let me know when the state of a tournament changes.
     * @param pub
     * @param code
     * @throws RemoteException 
     */
    @Override
    public void update(Object pub, Object code) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
