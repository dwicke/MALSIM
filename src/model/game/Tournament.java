/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import util.ObjectState;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.TreeMap;
import model.properties.game.TournamentProperties;
import util.Subscriber;

/**
 * This represents the tournament and is used to start the tournament.
 * @author drew
 */
@XStreamAlias("Tournament")
public class Tournament implements Subscriber, Runnable, Comparable {
    
    
    
    /**
     * The state of the game is mapped to the respected game
     */
    private TreeMap<Game, ObjectState> runningGames;
    private ObjectState state;
    
    TournamentProperties props;
    
    /**
     * Instantiates a properties object.
     */
    public Tournament()
    {
        setup();
    }
    
    /**
     * sets the tournprops to the arg
     * @param props 
     */
    public void setTournProps(TournamentProperties props)
    {
        this.props = props;
    }
    
    /**
     * Returns the tournament props
     * @return 
     */
    public TournamentProperties getTournProps()
    {
        return props;
    }
    /**
     * This starts the tournament
     */
    public void startTourn()
    {
        // while there are agents to play game/not stopped/not paused
        // while the number of games in the queue is less than the max num threads then
        // get the game properties from tournprops
        // get the name of the game toString
        // create a gamefactory object
        // get the game that matches the game props by providing the name of the game
        // call getAgentSel to get the agent selector from the tournprops
        // call nextContestants to get the agents that will play the game
        // if the returned agents are null then I know that I am done so
        // set my state to finished and notify the subscribers
        // set the agents to the game
        // Create a new ObjectState set this as the subscriber (to know when game is done)
        // set state to Runnable
        // assign the Objectstate to the Game object
        // start the game as a thread
        // add the game to the que
        // if finished running all games notify subscribers by changing
        // the state of the Tournprops to Finished
        // check if state has been set to Waiting
        // if so then call pauseTournament
        
    }
    /**
     * pause all running games
     */
    public void pauseTournament()
    {
        
    }
    
    /**
     * This will start all of the games that were paused
     */
    public void resumeTournament()
    {
        
    }
    /**
     * set the tournaments state
     * @param st 
     */
    public void setState(ObjectState st)
    {
        state = st;
    }
    
    /**
     * Get the tournament's state
     * @return 
     */
    public ObjectState getState()
    {
        return state;
    }
    
    /**
     * This is where all of the variables are setup
     */
    private void setup()
    {
        props = new TournamentProperties();
        runningGames = new TreeMap<Game, ObjectState>();
    }
    
    /**
     * This is used by xstream since constructor is not called
     * @return 
     */
    private Object readResolve() {
        setup();
        return this;
    }

    /**
     * Whenever a game's state changes I get notified.
     * So I will remove the game from the map if the game is finished
     * I will then call startTourn 
     * @param pub
     * @param code
     * @throws RemoteException 
     */
    @Override
    public void update(Object pub, Object code) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void run() {
        startTourn();
    }

    @Override
    public int compareTo(Object o) {
        if (o == this)
        {
            return 0;
        }
        else if (this.hashCode() > o.hashCode())
        {
            return 1;
        }
        return -1;
    }
}
