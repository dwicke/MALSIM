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
    /**
     * Instantiates a properties object.
     */
    public MPITourn() {
        super();
        // init the free tags
        try {
            tags = new TagList(MPJ.COMM_WORLD.size() - 1);
        } catch (MPJException ex) {
            Logger.getLogger(MPITourn.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        runner.setTag(tags.getFreeTag());
        // here is where I will
        // start running the game 
        setupGame(contestants, runner);
                
    }


   
}
