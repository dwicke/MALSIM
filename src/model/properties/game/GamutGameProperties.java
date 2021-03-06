/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import edu.stanford.multiagent.gamer.BattleOfTheSexes;
import edu.stanford.multiagent.gamer.Game;
import edu.stanford.multiagent.gamer.Global;
import edu.stanford.multiagent.gamer.MatrixGame;
import edu.stanford.multiagent.gamer.ParamParser;
import edu.stanford.multiagent.gamer.Parameters.ParamInfo;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author drew
 */
public class GamutGameProperties extends RepeatedGameProperties {

    // This is a gamut game not my Game
    protected Game game;
    private boolean wasGen;

    public GamutGameProperties() {
        super();
        wasGen = false;
    }

    @Override
    public void initProperties() {
        // The Factory already set the name that I will use
        game = (Game) Global.getObjectOrDie(toString(), Global.GAME);
        try {
            ParamParser par = new ParamParser("-players 4 -actions 3".split(" "));
            game.setParameters(par, true);
            game.initialize();
            game.doGenerate();
        } catch (Exception ex) {
            Logger.getLogger(GamutGameProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // set the num agents
        this.setNumAgents(game.getNumPlayers());
        // set the num actions
        this.setField("actions", 3);



        // players and actions are the only two parameters that are
        // ever necessary to be set in the Gamut matrix gamess
        // in order to get the name I have to do the initialize and doGenerate
        // and to call those I need to have already set players and actions
        //  ParamParser par = new ParamParser("-players 4 -actions 3".split(" "));
        //    game.setParameters(par, true);
        //  game.initialize();
        //game.doGenerate();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    /**
     * Some Gamut games don't let you set the players
     * @param numAgents 
     */
    @Override
    public void setNumAgents(int numAgents) {
        if (game.getParameter("players") == null) {
            this.numAgents = game.getNumPlayers();
        } else {
            this.numAgents = numAgents;
            /*// don't set the Game's parameters until starting the game...
            try {
            game.setParameter("players", numAgents);
            } catch (Exception ex) {
            Logger.getLogger(GamutGameProperties.class.getName()).log(Level.SEVERE, null, ex);
            }
             * 
             */
            this.fieldVals.put("players", numAgents);
        }
    }

    @Override
    public boolean setField(String fieldAlias, Object val) {

        System.out.println("The alias is " + fieldAlias + " wasgen " + wasGen + " my size " + this.fieldVals.size());
                

        for (int i = 0; i < game.getParameters().getNParams(); i++) {
            String fieldName = game.getParameters().getName(i);
            if (fieldName.equals(fieldAlias)) {
                // if there is then add it to the 
                this.fieldVals.put(fieldName, val.toString());

                return true;

            }
        }

        return super.setField(fieldAlias, val);
    }

    @Override
    public void generateViewFields() {
        wasGen = true;
        super.generateViewFields();
        // remove the "number of agents" since Gamut has it's own 
        this.fieldAlias.remove("number of agents");
        this.fieldVals.remove("number of agents");

        System.out.println(this.game.getName());
        //System.out.println("Generating views");
        //this.setField("number of strategies", numStrats);
        //this.setFieldClass("number of strategies", Integer.class);
        for (int i = 0; i < game.getParameters().getNParams(); i++) {
            if (this.fieldVals.containsKey(game.getParameters().getName(i)) == false) {
                if (game.getParameter(game.getParameters().getName(i)) != null
                        && game.getParameter(game.getParameters().getName(i)).toString() != null) {


                    if (game.getParameters().getName(i).equals("players")
                            || game.getParameters().getName(i).equals("actions")) {

                        this.fieldVals.put(game.getParameters().getName(i), game.getParameter(game.getParameters().getName(i)).toString().replace("[", "").replace("]", ""));
                    } else {
                        this.fieldVals.put(game.getParameters().getName(i), game.getParameter(game.getParameters().getName(i)).toString());
                    }
                } else {
                    this.fieldVals.put(game.getParameters().getName(i), "");
                }
            }
        }
System.out.println("The size of the fieldVals map is " + fieldVals.size());
    }

    @Override
    public String toString() {
        //System.out.println("In toString " + name + "  " + );
        return name;
    }

    @Override
    public String getDescription() {
        return game.getHelp();//game.getDescription();
    }
}
