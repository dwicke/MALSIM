/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.lang.Thread.State;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import util.BasicPublisher;
import util.ObjectState;
import util.Subscriber;

/**
 * This class is used to model all tournaments that will be played
 * @author drew
 */
public class Batch implements Subscriber{

    private TreeMap<Tournament, ObjectState> batch;
    private TreeMap<Tournament, Thread> tournThreads;
    private TreeMap<String, Tournament> tournNames;
    
    // used to notify subscribers about the changes to the tournaments
    // in the batch
    
    @XStreamOmitField
    private BasicPublisher publisher;
    
    private Object readResolve() {
        publisher = new BasicPublisher();
        return this;
    }

    public TreeMap<String, Tournament> getTournNamesMap() {
        return tournNames;
    }

    public void setTournNames(TreeMap<String, Tournament> tournNames) {
        this.tournNames = tournNames;
    }

    public TreeMap<Tournament, Thread> getTournThreads() {
        return tournThreads;
    }

    public void setTournThreads(TreeMap<Tournament, Thread> tournThreads) {
        this.tournThreads = tournThreads;
    }
    
    
    
    public Batch()
    {
        batch = new TreeMap<Tournament, ObjectState>();
        tournThreads = new TreeMap<Tournament, Thread>();
        tournNames = new TreeMap<String, Tournament>();
        publisher =  new BasicPublisher();
    }
    
    public void setPub(BasicPublisher pub)
    {
        this.publisher = pub;
    }
    public BasicPublisher getPub()
    {
        return this.publisher;
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
    
    public ArrayList<Tournament> getTournList()
    {
        return new ArrayList<Tournament> (batch.keySet());
    }
    
    
    public void startTournaments()
    {
        int id = 0;
        for (Tournament tourn : getTourn())
        {
            System.out.println(tourn.toString() + " Starting");
            // I start the tournaments as threads 
            // set the tournprops 
            //ObjectState st = new ObjectState(State.RUNNABLE, this, tourn);
            // I need to check if the getState is not null before i set this
            if (tournNames == null)
            {
                System.out.println("TOURNNAMES WAS NULL");
            }
            else
            {
                tournNames.put(tourn.toString(), tourn);
            }
            
            // must set the id of the tourn so that I can
            // id them via integer.  Also used for MPITourns
            // in order to partition the procs.
            tourn.setTournID(id);
            id++;
            
            tourn.setNumTourns(getTourn().size());
            
            tourn.getState().setState(State.RUNNABLE);
            tourn.getState().addSub(tourn);// so that it knows when to term
            
           
            
            // use an ArrayList of Threads since these threads will be running for a while
           
            
            Thread t = new Thread(tourn, tourn.toString());
            tournThreads.put(tourn, t);
            t.start();
             
        }
    }
    
    public void subBatch(Subscriber sub)
    {
        if (publisher == null)
        {
        System.out.println("PUBLISHER IS NULL WHEN ADDING");
        }
        else
        {
            publisher.addSubscriber(sub);
        }
    }
    
    public void subAllTourns(Subscriber sub)
    {
       
            System.out.println("Num tourns" + getTourn().size());
            // then add it to subscribe to all of the tourns
            for (Tournament tour : getTournList())
            {
                tour.addSub(sub);
            }
        
    }
    
    public void removeBatchSub(Subscriber sub)
    {
        if (publisher == null)
        {
        System.out.append("PUBLISHER IS NULL WHEN REMOVING");
        }
        else
        {
            publisher.removeSubscriber(sub);
        }
    }
    public void removeAllTournsSub(Subscriber sub)
    {
        
            for (Tournament tour : getTournList())
            {
                // remove the sub
                tour.removeSub(sub);
            }
        
    }
    public void pauseTourn(Tournament tourn)
    {
        // pause the tournament
        batch.get(tourn).setState(State.WAITING);
    }
    public void resumeTourn(Tournament tourn)
    {
        // resume the tournament
        batch.get(tourn).setState(State.RUNNABLE);
    }
    
    public void termTourn(Tournament tourn)
    {
        // resume the tournament
        batch.get(tourn).setState(State.TERMINATED);
    }
    public void pauseTourn(String tourn)
    {
        // pause the tournament
        batch.get(tournNames.get(tourn)).setState(State.WAITING);
    }
    public void resumeTourn(String tourn)
    {
        // resume the tournament
        batch.get(tournNames.get(tourn)).setState(State.RUNNABLE);
    }
    
    public void termTourn(String tourn)
    {
        // resume the tournament
        batch.get(tournNames.get(tourn)).setState(State.TERMINATED);
    }
    
    public ArrayList<String> getTournNames()
    {
        return new ArrayList<String>(tournNames.keySet());
    }
    
    public String getTournStateString(String tourn)
    {
        return tournNames.get(tourn).getState().getState().toString();
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
        if (publisher == null)
        {
            System.out.println("PUBLISHER IS NULL");
        }
        else
        publisher.notifySubscribers(pub, code);
        
        if (((Tournament)(code)).getState().getState().equals(State.RUNNABLE))
        {
            // don't do anything
            System.out.println("Batch have a running Tourn");
        }
        else if (((Tournament)(code)).getState().getState().equals(State.TERMINATED))
        {
            System.out.println("I have a term tourn");
            
            batch.get(code).removeSub(this);// remove from the sub list
            // remove the tournament from the running list
            tournThreads.remove((Tournament)code);
        }
    }
    
}
