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
    private TreeMap<Tournament, Thread> tournThreads;
    
    public Batch()
    {
        batch = new TreeMap<Tournament, ObjectState>();
        tournThreads = new TreeMap<Tournament, Thread>();
    }
    
    /**
     * Adds the tournament to the batch and sets the state to NEW.
     * @param tourn Tournament to be added.
     */
    public void addTournament(Tournament tourn)
    {
        ObjectState st = new ObjectState(State.NEW, this, tourn);
        tourn.setState(st);// I am pretty sure I need to do this
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
    
    public void startTournaments()
    {
        for (Tournament tourn : getTourn())
        {
            System.out.println(tourn.toString() + " Starting");
            // I start the tournaments as threads 
            // set the tournprops 
            //ObjectState st = new ObjectState(State.RUNNABLE, this, tourn);
            tourn.getState().setState(State.RUNNABLE);
           // tourn.setState(st);
            
            // use an ArrayList of Threads since these threads will be running for a while
            Thread t = new Thread(tourn, tourn.toString());
            tournThreads.put(tourn, t);
            t.start();
        }
    }
    
    /**
     * This will be used to let me know when the state of a tournament changes.
     * not sure why i need to know but I will
     * @param pub
     * @param code
     * @throws RemoteException 
     */
    @Override
    public void update(Object pub, Object code) throws RemoteException {
        // the code is the Tournament
        
        if (((Tournament)(code)).getState().getState().equals(State.RUNNABLE))
        {
            // don't do anything
            System.out.println("I have a running Tourn");
        }
        else if (((Tournament)(code)).getState().getState().equals(State.TERMINATED))
        {
            System.out.println("I have a term tourn");
        }
    }
    
}
