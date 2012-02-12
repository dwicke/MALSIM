/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game.games;

import edu.stanford.multiagent.gamer.ParamParser;
import edu.stanford.multiagent.gamer.Parameters;
import java.lang.Thread.State;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.agent.Agent;
import model.agent.GamutAgent;
import model.game.Game;
import model.properties.game.GamutGameProperties;
import model.properties.game.RepeatedGameProperties;

/**
 *
 * @author drew
 */
public class GamutGame extends Game{
    private edu.stanford.multiagent.gamer.Game g;

    
    public GamutGame() {
        super();
        this.setGameProperties(new GamutGameProperties());
    }
    
    protected String createParamString()
    {
        StringBuilder builder = new StringBuilder();
        Parameters pars = getGameProps().getGame().getParameters();
        for (int i = 0; i < pars.getNParams(); i++)
        {
            Object ob = getGameProps().getField(pars.getName(i));
            
            if (ob != null && !ob.toString().trim().equals(""))
            {
                builder.append(" -").append(pars.getName(i)).append(" ").append(getGameProps().getField(pars.getName(i)).toString());
            }
        }
        System.out.println(builder.toString());
        return builder.toString().trim();
    }
    
    public void setupGamutGame()
    {
       // g = null;// = this.getGameProps().getGame().getClass().newInstance();
        try {
            g = this.getGameProps().getGame().getClass().newInstance();
            String params = createParamString();
            if (!params.equals(""))
            {
                g.setParameters(new ParamParser(createParamString().split(" ")), true);
            }
            g.initialize();
        } catch (Exception ex) {
            Logger.getLogger(GamutGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.doGenerate();
       // this.getGameProps().setGame(g); // don't set other wise all the other games will have
                                              // the same setup
        int order = 1;
        for (GamutAgent ag : this.getGamutAgents())
        {
            ag.setMatrix(g);
            ag.setOrder(order);
            order++;
        }
    }
    
    @Override
    public GamutGameProperties getGameProps()
    {
        return (GamutGameProperties)props;
    }
    
    public ArrayList<GamutAgent> getGamutAgents() throws ClassCastException
    {
        ArrayList<GamutAgent> matrixAgents = new ArrayList<GamutAgent>();
        for (Agent ag : players)
        {
            if (ag instanceof GamutAgent)
            {
                matrixAgents.add((GamutAgent)ag);
            }
            else
            {
                throw new ClassCastException("Agent was not a GamutAgent.");
            }
        }
        return matrixAgents;
    }
    
    
    /**
     * Must check that if the game state is set to waiting that
     * I call wait on this.
     */
    @Override
    public void startGame() {
        
        System.out.println("Started game...");
        // generate a matrix and setup the agents to play
        this.setupGamutGame();
        
        System.out.println("Finished game setup " + getGameProps().getNumReps());
        
      //  ArrayList<Integer> actions = new ArrayList<Integer>();
        int actions[] = new int[getGamutAgents().size()];
        // give the two agents the 
        for (int i = 0; i < getGameProps().getNumReps(); i++)
        {
            System.out.println("Current game " + i);
            checkPaused();// pause if necessary
            // TODO must check if terminated
            
            int j = 0;
            // get the actions from each of the agents
            for (GamutAgent ag : getGamutAgents())
            {
                ag.takeTurn();
                actions[ag.getOrder() - 1] = ag.getAction();
            }
            
           // System.out.println("After");
            
            
            // now I can assign payoffs to the agents
            for (GamutAgent ag : getGamutAgents())
            
            //System.out.println("Number of agents" + getGamutAgents().size());
           // for (int h = 0; h < getGamutAgents().size(); h++)
            {
                // i must use the local game the game props just tells me what
                // type of game i am to play
                ag.addScore(g.getPayoff(actions, ag.getOrder() - 1 ));
                
              //  System.out.println("Agent 0 score is now " + getGamutAgents().get(0).getScore());
            }
           // System.out.println("Number of agents" + getGamutAgents().size());
        }
    }



    @Override
    public void setAgents(ArrayList<Agent> agents) {
        super.setAgents(agents);
    }
    

    

    @Override
    public void update(Object pub, Object code) throws RemoteException {
       // throw new UnsupportedOperationException("Not supported yet.");
    }

    
}
