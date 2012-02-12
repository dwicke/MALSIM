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
import model.game.RepeatedGame;
import model.properties.game.GamutGameProperties;
import model.properties.game.RepeatedGameProperties;

/**
 *
 * @author drew
 */
public class GamutGame extends RepeatedGame {

    private edu.stanford.multiagent.gamer.Game g;

    public GamutGame() {
        super();
        this.setGameProperties(new GamutGameProperties());
    }

    protected String createParamString() {
        StringBuilder builder = new StringBuilder();
        Parameters pars = getGameProps().getGame().getParameters();
        for (int i = 0; i < pars.getNParams(); i++) {
            Object ob = getGameProps().getField(pars.getName(i));

            if (ob != null && !ob.toString().trim().equals("")) {
                builder.append(" -").append(pars.getName(i)).append(" ").append(getGameProps().getField(pars.getName(i)).toString());
            }
        }
        System.out.println(builder.toString());
        return builder.toString().trim();
    }

    public void setupGamutGame() {
        // g = null;// = this.getGameProps().getGame().getClass().newInstance();
        try {
            g = this.getGameProps().getGame().getClass().newInstance();
            String params = createParamString();
            if (!params.equals("")) {
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
        for (GamutAgent ag : this.getGamutAgents()) {
            ag.setMatrix(g);
            ag.setOrder(order);
            order++;
        }
    }

    @Override
    public GamutGameProperties getGameProps() {
        return (GamutGameProperties) props;
    }

    public ArrayList<GamutAgent> getGamutAgents() throws ClassCastException {
        ArrayList<GamutAgent> matrixAgents = new ArrayList<GamutAgent>();
        for (Agent ag : players) {
            if (ag instanceof GamutAgent) {
                matrixAgents.add((GamutAgent) ag);
            } else {
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
        System.out.println("Finished game setup starting game with " + getGameProps().getNumReps() + " reps");
        
        super.startGame();
        System.out.append("Finshed repeated GamutGame");
    }

    @Override
    protected void repeatedAction() {
        int actions[] = new int[getGamutAgents().size()];
        // get the actions from each of the agents
        for (GamutAgent ag : getGamutAgents()) {
            ag.takeTurn();
            actions[ag.getOrder() - 1] = ag.getAction();
        }

        // System.out.println("After");


        // now I can assign payoffs to the agents
        for (GamutAgent ag : getGamutAgents()) {
            // i must use the local game the game props just tells me what
            // type of game i am to play
            ag.addScore(g.getPayoff(actions, ag.getOrder() - 1));
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
