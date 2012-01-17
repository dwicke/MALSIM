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
            if (ob != null && ob.toString() != "")
            {
                builder.append(" -").append(pars.getName(i)).append(" ").append(getGameProps().getField(pars.getName(i)).toString());
            }
        }
        System.out.println(builder.toString());
        return builder.toString().trim();
    }
    
    public void setupGamutGame()
    {
        edu.stanford.multiagent.gamer.Game g = null;// = this.getGameProps().getGame().getClass().newInstance();
        try {
            g = this.getGameProps().getGame().getClass().newInstance();
            g.setParameters(new ParamParser(createParamString().split(" ")), true);
            g.initialize();
        } catch (Exception ex) {
            Logger.getLogger(GamutGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.doGenerate();
        this.getGameProps().setGame(g);
        int order = 1;
        for (GamutAgent ag : this.getMatrixAgents())
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
    
    public ArrayList<GamutAgent> getMatrixAgents() throws ClassCastException
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
                throw new ClassCastException("Agent was not a MatrixAgent.");
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
        // generate a matrix and setup the agents to play
        this.setupGamutGame();
        
      //  ArrayList<Integer> actions = new ArrayList<Integer>();
        int actions[] = new int[getMatrixAgents().size()];
        // give the two agents the 
        for (int i = 0; i < getGameProps().getNumReps(); i++)
        {
            checkPaused();// pause if necessary
            
            // get the actions from each of the agents
            for (GamutAgent ag : getMatrixAgents())
            {
                ag.takeTurn();
                actions[ag.getOrder()] = ag.getAction();
            }
            
            
            
            
            // now I can assign payoffs to the agents
            for (GamutAgent ag : getMatrixAgents())
            {
                ag.addScore(getGameProps().getGame().getPayoff(actions, ag.getOrder()));
            }
            
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
