/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
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
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import util.BasicPublisher;

/**
 * This represents the tournament and is used to start the tournament.
 * @author drew
 */
public class Tournament implements Subscriber, Runnable, Comparable {

    /**
     * The state of the game is mapped to the respected game
     */
    protected ExecutorService threadPool;
    protected Map<GameRunner, Game> runningGames;
    protected ObjectState state;
    protected TournamentProperties props;
    protected String name;
    protected List<Agent> remainingAgents, eliminatedAgents, removeLater;
    protected boolean paused;
    
    @XStreamOmitField
    protected GenericFactory fac;
    protected int tournID;// used to uniquely id this tourn
    protected int numTourns;// the number of tourns there are
    protected int runnerId;
    @XStreamOmitField
    private BasicPublisher publisher = new BasicPublisher();
    
    //private final Object agentMutex = new Object();
    
    
    /**
     * Instantiates a properties object.
     */
    public Tournament() {
        setup();
    }

    private Object readResolve() {
        fac = new GenericFactory();
        // BAD don't want magic strings...
        fac.generateMaping("config/GameList.cfg");
        publisher = new BasicPublisher();
        return this;
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
    
    public void setupTourn()
    {
        threadPool = Executors.newFixedThreadPool(props.getNumMaxThreads());
        //remainingAgents = props.getAgents(); // this made it so that the props agents was the remaining agents
        remainingAgents.clear();
        for (Agent ag : props.getAgents())
        {
            remainingAgents.add(ag);
        }
        synchronized(eliminatedAgents)//probably don't need to do this but just in case
        {
            // now remove any of the eliminated agents
            for (Agent ag : eliminatedAgents) {
                remainingAgents.remove(ag);
            }
        }
        props.getAgentSelector().setPlayers(remainingAgents);
        props.getAgentSelector().setNumToSelect(props.getGameProps().getNumAgents());
        // first check if there are games that were started and saved and now need to be restarted
        for (GameRunner g : runningGames.keySet()) {

            // start the game as a thread
            threadPool.submit(g);
        }
    }

    /**
     * This starts the tournament
     */
    public void startTourn()  {

        // while there are agents to play game/not stopped/not paused
        // while the number of games in the queue is less than the max num threads then

       // while (paused == false && competitorsAvail() == true) {
        ArrayList<Agent> contestants = null;
        
        // I am going to try sync and waiting on this
        // then instead of calling startTourn in the update 
        // method i will call notify 
        synchronized(this)
        {
            while(state.getState() != State.TERMINATED)
            {
                // i don't think i do the check on the num runnning since the thread pool will take care of limiting the number
                // of running threads
                //while(paused == false && runningGames.size() < props.getNumMaxThreads() && (contestants = props.getAgentSelector().nextContestants()) != null && !contestants.isEmpty())
                while(state.getState() == State.RUNNABLE && (contestants = props.getAgentSelector().nextContestants()) != null && !contestants.isEmpty())
                {
                    System.err.println("contestants " + contestants);
                    System.err.println("inside while loop for tourn");
                   setupTournGame(contestants);

                }
            
                try {
                    this.wait(30000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.err.println("Tourn loop woken up");
                System.err.println(runningGames.size() + " " + props.getNumMaxThreads() + " " + props.getAgentSelector().nextContestants());
            }
        }
       // System.out.println("IH");
        //threadPool.shutdown();
       // System.out.println(this.toString() + " Finished");
        // if the returned agents are null then I know that I am done so
        // set my state to finished and notify the subscribers

    }
    
    public void setupGame(ArrayList<Agent> contestants, GameRunner runner)
    {
        GameProperties gameProps = props.getGameProps();
                // get the name of the game toString
                String gameName = gameProps.toString();

                // get the game that matches the game props by providing the name of the game
                Game game = (Game) fac.getObject(gameName);
                // set the game's properties this might not be good if gameProps is not usable by all games
                game.setGameProperties(gameProps);
                // set the agents to the game
                game.setAgents(contestants);
                // Create a new ObjectState set this as the subscriber (to know when game is done)
                // set state to Runnable
                ObjectState obState = new ObjectState(State.RUNNABLE, this, game);
                // set the Agent's state to running
                for (Agent ag : contestants)
                {
                    // no one will be notified about the state of the agent
                    // this can change if neccessary...
                    ag.setState(new ObjectState(State.RUNNABLE));
                }
                
                //System.out.println("The number of agents left is " + );
                // assign the Objectstate to the Game object
                game.setObjectState(obState);
                
                runner.setGame(game);
                // add it to the running games
                runningGames.put(runner, game);
                // start the game as a thread
                threadPool.submit(runner);
    }
    public void setupTournGame(ArrayList<Agent> contestants)
    {
        //  System.out.println("Starting first game");
         // get the game properties from tournprops
                
                
                GameRunner runner = null;
        try {
            runner = GameRunnerFactory.getGameRunner();
        } catch (MPJException ex) {
            Logger.getLogger(MPITourn.class.getName()).log(Level.SEVERE, null, ex);
        }
        runner.setID(runnerId);
        runnerId++;
        // here is where I will 
        setupGame(contestants, runner);
                
    }

    /**
     * pause all running games
     */
    public void pauseTournament() {
        paused = true;
        System.err.println("in pause tournament" + runningGames.keySet().size() + "  " + runningGames.size());
        synchronized(runningGames)
        {
            for (GameRunner g : runningGames.keySet()) {
                System.err.println("TOURN pausing games");
                g.pauseGame();
            }
        }
    }

    /**
     * This will start all of the games that were paused
     */
    public void resumeTournament() {
        paused = false;
        // games are in seperate threads so they can call wait
        // so I must notify them
        for (GameRunner g : runningGames.keySet()) {
            g.resumeGame();
        }
      //  paused = false;
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
        //runningGames = new TreeMap<GameRunner, Game>();
        runningGames = Collections.synchronizedMap(new TreeMap<GameRunner, Game>());
        remainingAgents = Collections.synchronizedList(new ArrayList<Agent>());
        eliminatedAgents = Collections.synchronizedList(new ArrayList<Agent>());
        removeLater = Collections.synchronizedList(new ArrayList<Agent>());
        //remainingAgents = new ArrayList<Agent>();
        //eliminatedAgents = new ArrayList<Agent>(); 
        //removeLater = new ArrayList<Agent>(); 
        paused = false;
        // create a gamefactory object
        fac = new GenericFactory();
        // BAD don't want magic strings...
        fac.generateMaping("config/GameList.cfg");
        // the id for the game runners
        runnerId = 0;
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
        System.out.println("I am printing game " + code);
        if (publisher != null)
        {
            System.out.println("NOTIFYING SUBS");
            publisher.notifySubscribers(this);
        }
        // if the tournament was set to be terminated
        if (state.getState() == State.TERMINATED)
        {
            //shutdown the games
            System.out.println("Tournament was terminated remotely");
            state.removeSub(this);
            synchronized(runningGames)
            {
                for (GameRunner g : runningGames.keySet())
                {

                    // only problem might be race conditions
                    // if a game terminates since it is finished
                    // then I might get notified via the gamerunner thread
                    // the the next line will be called
                    g.getGame().getGameState().removeSub(this);
                    g.shutdownRunner();// shut it down don't just term the game
                    //g.terminateGame();


                }
            }
            // no more running games so clear it
            runningGames.clear();
            return;
        }

        // if the game is terminated
        if (code instanceof Game && (State) ((ObjectState) pub).getState() == State.TERMINATED) {
            System.out.println("I am terminating game");
            // eliminate an agent from the remaining list
            // new problem when I go to eliminate an agent the
            // score will be at where ever it is at when I call this
            // won't be fair maybe the other agent hasn't even played a whole
            // game yet that will be decided by the eliminator

            // If the eliminator eliminated a player that is currently playing
            // then it was marked for removal so I will remove the players that 
            // were marked that are in the game that just terminated

            ArrayList<Agent> store = new ArrayList<Agent>();
            // must do it this way other wise may end up in dead lock
            // since i am syncing on remove later and then must sync on
            // on remainingagents some other thread could be syncing on
            // remainingagents and waiting for removelater.  so i made
            // the store list that way i don't need to wory about that.
            synchronized(removeLater)
            {
                for (Agent el : removeLater)
                {
                    if (((Game) code).getAgents().contains(el))
                    {
                        store.add(el);
                    }
                }
            }
            
            for (Agent el : store)
            {
                remainingAgents.remove(el);
                eliminatedAgents.add(el);
                ((Game) code).getAgents().remove(el);
            }




            System.out.println("Finished establinshing the agents that remain");

            Agent elimAgent = props.getEliminator().eliminate(remainingAgents);

            // If the eliminated Agent is Runnable then I will need to add the
            // agent to the remove later list
            if (elimAgent != null && elimAgent.getAgentObjectState() != null && 
                    elimAgent.getAgentObjectState().getState() == State.RUNNABLE)
            {
                System.out.println("Will remove the agent later");
                removeLater.add(elimAgent);
            }
            else if (elimAgent != null) {
                System.out.println("Removed the agent now");
                remainingAgents.remove(elimAgent);
                eliminatedAgents.add(elimAgent);
            }
            // So now I can set the state of the Agents in the game to Blocked
            // meaning that it is ready to be selected again
            for (Agent ag : ((Game) code).getAgents())
            {
                // as long as it is not the eliminated agent
                if (ag.compareTo( elimAgent) != 0)
                    ag.getAgentObjectState().setState(State.BLOCKED);
            }
            System.out.println("The number of agents left " + remainingAgents.size());
            // update the players to choose from for the AgentSelector
            props.getAgentSelector().setPlayers(remainingAgents);
            // remove the game from the running list
            //runningGames.remove((Game) code);

            runningGames.values().remove((Game)code);
            System.out.println("Removed Game from running list games left " + runningGames.size());
         //   System.out.println(((Game) code).toString());
        }

        System.out.println((State) ((ObjectState) pub).getState());


        // move this to the update method and have Tournament subscribe to the state
        // so then it will be updated and I will need to check that the pub arg is
        // equal to state field then if it is WAITING then I go inside the if
        if (state.getState() == State.WAITING && paused == false) {
            System.out.println("The tournament should pause");
            paused = true;
            // pause the games
            pauseTournament();
            // pause this thread then the batch will call notify on this thread.
            // I don't think I should call wait since I won't be in a seperate thread
           /* synchronized (this) {
                try {
                    
                    //wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tournament.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
            
            // done waiting so resume the games
            //resumeTournament();
            
        }
        else if (state.getState() == State.RUNNABLE && paused == true)
        {
            
            System.out.println("Should resume");
            resumeTournament();
        }
       
        System.out.println("Comps avail: " + competitorsAvail() + " running games empty:" + 
                runningGames.isEmpty() + " is paused " + paused);
        // once running games is empty and I am not paused and agentselector produces
        // no more competitors then Tournament is completed 
        // I will remove myself as a subscriber to my own object state
        // then I will set my state to Terminated and then batch will be
        // updated as to my status and I will return from update and will exit this part
        if (competitorsAvail() == false && runningGames.isEmpty() == true  )
        {
            System.out.println("Tourn is term");
            state.removeSub(this);
            state.setState(State.TERMINATED);
        }
        else if (paused == false && getState().getState() != State.TERMINATED)
        {
            // if the tournament is not paused or wasn't terminated then 
            // start a game
            System.out.println("Restarting startTourn");
            // I still have games to play
            
                synchronized(this)
                {
                    this.notify();
                }
            
            //startTourn(); // uncomement this if the sync doesn't work
        }
        
        
    }

    @Override
    public void run() {
        setupTourn();
        startTourn();
    }

    @Override
    public String toString() {
        return name;
    }

    public void setString(String newName) {
        name = newName;
    }
    
    /**
     * The id is used to identify the tourn by
     * an integer.  The id is sequentially assigned
     * @return 
     */
    public int getTournID() {
        return tournID;
    }

    public void setTournID(int tournID) {
        this.tournID = tournID;
    }
    
    public int getNumTourns() {
        return numTourns;
    }

    public void setNumTourns(int numTourns) {
        this.numTourns = numTourns;
    }
    
    public Agent getLeadAgent()
    {
        Agent ag = null;
        synchronized(remainingAgents)
        {
            for (Agent agent : remainingAgents)
            {
                if (ag == null || agent.getScore() > ag.getScore())
                {
                    ag = agent;
                }
            }
        }
        return ag;
    }
    
    public Map<GameRunner, Game> getRunningGames()
    {
        // unmodifiable stores a reference to the collection i gave
        // it so even though the caller has a unmodifiable collection
        // the values may change because I am changing them back here.
        // so I return like this but I require the user to sync on the 
        // collection to read it and expect them to not change it.
        return runningGames;
    }

    public void addSub(Subscriber sub)
    {
        publisher.addSubscriber(sub);
    }
    public void removeSub(Subscriber sub)
    {
        publisher.removeSubscriber(sub);
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

    public String getGameName(int row) {
        
        synchronized(runningGames)
        {
            if (row < runningGames.size())
            {
                int i = 0;
                for (Game g : runningGames.values())
                {
                    if (i == row)
                    {
                        return g.getGameProps().toString();
                    }
                    i++;
                }
            
            }
        }
        return "";
    }

    Object getGameState(int row) {
        synchronized(runningGames)
        {
            if (row < runningGames.size())
            {
                int i = 0;
                for (Game g : runningGames.values())
                {
                    if (i == row)
                    {
                        return g.getGameState().getState().toString();
                    }
                    i++;
                }
            
            }
        }
        return "";
    }

    Object getAgent(int row, Integer agentIndex) {
        synchronized(runningGames)
        {
            if (row < runningGames.size())
            {
                int i = 0;
                for (Game g : runningGames.values())
                {
                    if (i == row)
                    {
                        return g.getAgents().get(agentIndex).toString();
                    }
                    i++;
                }
            
            }
        }
        return "";
    }
}
