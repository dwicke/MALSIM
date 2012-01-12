/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

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
public class Tournament implements Subscriber, Runnable, Comparable {

    /**
     * The state of the game is mapped to the respected game
     */
    private ExecutorService threadPool;
    private TreeMap<Game, ObjectState> runningGames;
    private ObjectState state;
    private TournamentProperties props;
    private String name;
    private ArrayList<Agent> remainingAgents;

    /**
     * Instantiates a properties object.
     */
    public Tournament() {
        setup();
    }

    /**
     * sets the tournprops to the arg
     * @param props 
     */
    public void setTournProps(TournamentProperties props) {
        this.props = props;
    }

    /**
     * Returns the tournament props
     * @return 
     */
    public TournamentProperties getTournProps() {
        return props;
    }

    /**
     * This starts the tournament
     */
    public void startTourn() {

        threadPool = Executors.newFixedThreadPool(props.getNumMaxThreads());
        
        // first check if there are games that were started and saved and now need to be restarted
        for (Game g : runningGames.keySet()) {
            
            // start the game as a thread
            threadPool.submit(g);
        }


        // while there are agents to play game/not stopped/not paused
        // while the number of games in the queue is less than the max num threads then
        while (competitorsAvail() == true) {

            if (state.getState() == State.WAITING) {
                // pause the games
                pauseTournament();
                // pause this thread then the batch will call notify on this thread.
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                // done waiting so resume the games
                resumeTournament();
            }

            if (runningGames.size() < props.getNumMaxThreads()) {
                // get the game properties from tournprops
                GameProperties gameProps = props.getGameProps();
                // get the name of the game toString
                String gameName = gameProps.toString();
                // create a gamefactory object
                GenericFactory fac = new GenericFactory();
                // get the game that matches the game props by providing the name of the game
                Game game = (Game) fac.getObject(gameName);
                // call nextContestants to get the agents that will play the game
                ArrayList<Agent> contestants = props.getAgentSelector().nextContestants();
                // set the agents to the game
                game.setAgents(contestants);
                // Create a new ObjectState set this as the subscriber (to know when game is done)
                // set state to Runnable
                ObjectState obState = new ObjectState(State.RUNNABLE, this, game);
                // assign the Objectstate to the Game object
                game.setObjectState(obState);
                // start the game as a thread
                threadPool.submit(game);
            }
        }

        System.out.println(this.toString() + " Finished");
        // if the returned agents are null then I know that I am done so
        // set my state to finished and notify the subscribers

    }

    /**
     * pause all running games
     */
    public void pauseTournament() {
        for (Game g : runningGames.keySet()) {
            runningGames.get(g).setState(State.WAITING);
        }
    }

    /**
     * This will start all of the games that were paused
     */
    public void resumeTournament() {
    }

    /**
     * set the tournaments state
     * @param st 
     */
    public void setState(ObjectState st) {
        state = st;
    }

    /**
     * Get the tournament's state
     * @return 
     */
    public ObjectState getState() {
        return state;
    }

    /**
     * This is where all of the variables are setup
     */
    private void setup() {
        props = new TournamentProperties();
        runningGames = new TreeMap<Game, ObjectState>();
        remainingAgents = new ArrayList<Agent>();
        
    }

    /**
     * Whenever a game's state changes I get notified.
     * So I will remove the game from the map if the game is finished
     * @param pub
     * @param code
     * @throws RemoteException 
     */
    @Override
    public void update(Object pub, Object code) throws RemoteException {
        if ((State)((ObjectState) pub).getState() == State.TERMINATED) {
            // eliminate an agent from the remaining list
            remainingAgents.remove(props.getEliminator().eliminate(remainingAgents));
            // update the players to choose from for the AgentSelector
            props.getAgentSelector().setPlayers(remainingAgents);
            // remove the game from the running list
            runningGames.remove((Game) code);
            System.out.println(((Game)code).toString());
        }

    }

    @Override
    public void run() {
        startTourn();
    }

    @Override
    public String toString() {
        return name;
    }

    public void setString(String newName) {
        name = newName;
    }

    @Override
    public int compareTo(Object o) {
        return this.toString().compareTo(o.toString());
    }

    private boolean competitorsAvail() {
        // call getAgentSel to get the agent selector from the tournprops
        // set status to finished and notify observers and return false
        return props.getAgentSelector().hasContestants();
    }
}
