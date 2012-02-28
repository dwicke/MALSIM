/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import ibis.mpj.MPJ;
import ibis.mpj.MPJException;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.ObjectState;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TreeMap;
import model.agent.Agent;
import model.properties.game.GameProperties;
import model.properties.game.TournamentProperties;
import util.GenericFactory;
import util.Subscriber;
import java.lang.Thread.State;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This represents the tournament and is used to start the tournament.
 * @author drew
 */
public class MPITourn extends Tournament {

    // free tags of all of the tourns
    // first come first serve
    private static TagList tags;
    int start, stop;
    /**
     * Instantiates a properties object.
     */
    public MPITourn() {
        super();
        // init the free tags
       /* try {
            // must ignore 0 proc to do that i reduce the size and then must increment
           // getStart();
            
          //  getStop();
            
            //tags = new TagList(start, stop);
        } catch (MPJException ex) {
            Logger.getLogger(MPITourn.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    private int getStart() throws MPJException
    {
        start = (MPJ.COMM_WORLD.size() - 1) / numTourns * tournID + 1;
        return start;
    }

    private int getStop() throws MPJException
    {
        
        if (tournID == (numTourns - 1))
        {
                stop = MPJ.COMM_WORLD.size();
        }
        else
        {
            stop = getStart() + (MPJ.COMM_WORLD.size() - 1) / numTourns - 1;
        }
        return stop;
    }
   
    @Override
    public void setupTournGame(ArrayList<Agent> contestants)
    {
                MPIGameRunner runner = null;
        try {
            runner = (MPIGameRunner)GameRunnerFactory.getGameRunner();
        } catch (MPJException ex) {
            Logger.getLogger(MPITourn.class.getName()).log(Level.SEVERE, null, ex);
        }
        // set the Tag so that I can comm with the proc
        // that will runn the game ie MPIGameClient
        if (tags == null)
        {
            try {
                getStart();
                getStop();
                tags = new TagList(start, stop);
            } catch (MPJException ex) {
                Logger.getLogger(MPITourn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.err.print("Starting to get a new tag");
       // Tag tag = tags.getFreeTag();
        runner.setTagList(tags);// must provide the whole list so that it can
        // it can get a new tag if its proc dies.
        
        //System.out.println("Runner has the tag: " + tag);
        // here is where I will
        // start running the game 
        setupGame(contestants, runner);
        
                
    }


   
}
