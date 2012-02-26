/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import com.thoughtworks.xstream.XStream;
import ibis.mpj.MPJ;
import ibis.mpj.MPJException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.agent.Agent;
import util.GameFactory;
import util.MPIRecvOverseer;
import util.ObjectState;
import util.XMLSerial;

/**
 * need to set the ObjectState of the
 * initial game to the finished game received
 * from mpigameclient
 * set the state to Terminated then the Tournament
 * will know
 * @author drew
 */
public class MPIGameRunner extends ThreadedGameRunner{
    
    private Tag usedTag;
    private final Object hook = this;
    private boolean shouldTerm = false;
    
    
    public void setTag(Tag tag)
    {
        usedTag = tag;
    }
    
    @Override
    public void startGame() {
        
        
        //XStream x = new XStream();
        // this works but not efficient
        // for some reason if i just xml the game i am
        // using it doesn't work.  XStream gets things
        // not even releated to a Game object
        // I think that it has to do with the fact that
        // XStream is not thread safe and that somehow
        // it is not working because of that.
        
        GameFactory gf = new GameFactory();
        gf.generateMaping();
        Game d = (Game)gf.getObject(g.getGameProps().toString());
        d.setAgents(g.getAgents());
        d.setGameProperties(g.getGameProps());
        String xml = XMLSerial.x.toXML(d);
        
        
       // System.out.println(xml + "");
        MPIRecvOverseer.hook(hook, usedTag);
       
       
       // set that I want to know when i get something
       
        // use the MPIRecvOverseer no iprobe and recv
       synchronized(hook)
       {
           
           // I put this inside the sync to be safe
           // but I don't think that it is necessary
           sendObject(xml);
           
           while(MPIRecvOverseer.probe(usedTag) == false && shouldTerm == false)
           {
                try {
                    
                    hook.wait();
                    System.err.println("Got woken up");
                } catch (InterruptedException ex) {
                    Logger.getLogger(MPIGameRunner.class.getName()).log(Level.SEVERE, null, ex);
                }
           }
       }
       // must releast the tag first otherwise the other thread won't have use of it
       MPIRecvOverseer.unhook(usedTag);
       usedTag.setFinished();
       if (shouldTerm == false)
       {
       
       String ob = (String)MPIRecvOverseer.getNextObject(usedTag);
       Game gameReturned = (Game)XMLSerial.x.fromXML(ob);
       
        
        System.out.println("The ob recv is " + ob);
        g.setGameProperties(gameReturned.getGameProps());
        for (Agent ag : g.getAgents())
        {
            ag.resetScore();
            Agent old = gameReturned.getAgents().get(gameReturned.getAgents().indexOf(ag));
            ag.addScore(old.getScore());
            ag.getAgentObjectState().setState(old.getAgentObjectState().getState());
            
        }
        g.getGameState().setState(Thread.State.TERMINATED);
        
        
       }
       else
       {
           System.out.println("Term IN MPIGAMERUNNER");
       }
       
    }

    @Override
    public void pauseGame() {
        ObjectState st = new ObjectState(Thread.State.WAITING);
        sendObject(st);
    }

    @Override
    public void resumeGame() {
        ObjectState st = new ObjectState(Thread.State.RUNNABLE);
        sendObject(st);
    }

    /**
     * tourn calls this to terminate a game.
     */
    @Override
    public void terminateGame() {
        ObjectState st = new ObjectState(Thread.State.TERMINATED);
        sendObject(st);
    }
    
    @Override
    public void shutdownRunner()
    {
        // first shutdown the game
        terminateGame();
        // then shutdown the proc
        terminateGame();
        
        // then shut down me
        shouldTerm = true;
        synchronized(hook)
        {
            hook.notify();
        }
    }
    
    private void sendObject(Object st)
    {
        Object[] termState = new Object[1];
        termState[0] = st;
        try {
            
            MPJ.COMM_WORLD.send(termState, 0, 1, MPJ.OBJECT, usedTag.getTag(), usedTag.getTag());
        } catch (MPJException ex) {
            Logger.getLogger(MPIGameRunner.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
