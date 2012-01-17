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
public class GamutGameProperties extends MatrixGameProperties {

    
    public GamutGameProperties() {
        super();
    }
    
    @Override
    public void initProperties()
    {
        // The Factory already set the name that I will use
        game = (MatrixGame) Global.getObjectOrDie(toString(), Global.GAME);
        // players and actions are the only two parameters that are
            // ever necessary to be set in the Gamut matrix gamess
            // in order to get the name I have to do the initialize and doGenerate
            // and to call those I need to have already set players and actions
      //  ParamParser par = new ParamParser("-players 4 -actions 3".split(" "));
        //    game.setParameters(par, true);
          //  game.initialize();
            //game.doGenerate();
    }
    

    /**
     * Some Gamut games don't let you set the players
     * @param numAgents 
     */
    @Override
    public void setNumAgents(int numAgents) {
        if (game.getParameter("players") == null)
        {
            this.numAgents = game.getNumPlayers();
        }
        else
        {
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

        

        for (int i = 0; i < game.getParameters().getNParams(); i++) {
            String fieldName = game.getParameters().getName(i);
            if (fieldName.equals(fieldAlias)) {
                // if there is then add it to the 
                this.fieldVals.put(fieldName, val.toString());
                
                
                
                /*
                try {
                    switch (game.getParameters().getParamInfo()[i].type) {
                        case ParamInfo.LONG_PARAM:
                            long lval = (long) parseDouble(val, game.getParameters().getLongParameter(fieldName));
                            game.setParameter(fieldAlias, lval);
                            this.fieldVals.put(fieldAlias, lval);
                            break;
                        case ParamInfo.DOUBLE_PARAM:
                            double dval = parseDouble(val, game.getParameters().getDoubleParameter(fieldName));
                            game.setParameter(fieldAlias, dval);
                            this.fieldVals.put(fieldAlias, dval);
                            break;
                        case ParamInfo.STRING_PARAM:
                            game.setParameter(fieldAlias, val.toString());
                            this.fieldVals.put(fieldAlias, val.toString());
                            break;
                        case ParamInfo.BOOLEAN_PARAM:
                            game.setParameter(fieldAlias, game.getParameters().getParamInfo()[i].defaultValue);
                            System.out.println("Boolean parameters not implemented.");
                            break;
                        case ParamInfo.VECTOR_PARAM:
                            // maybe set the default value if possible
                            System.out.println("Vector parameters not implemented.");
                            break;
                        case ParamInfo.CMDLINE_PARAM:
                            // set default value if possible...
                            System.out.println("CMDLine parameters not implemented.");
                            break;
                        default:
                            System.out.println("Unknown parameter not implemented.");
                            break;
                    }
                    */

                    return true;
                
            }
        }
        
        return super.setField(fieldAlias, val);
    }

    @Override
    public void generateViewFields() {
        super.generateViewFields();

        System.out.println(this.game.getName());
        //System.out.println("Generating views");
        //this.setField("number of strategies", numStrats);
        //this.setFieldClass("number of strategies", Integer.class);
        for (int i = 0; i < game.getParameters().getNParams(); i++) {
            this.fieldVals.put(game.getParameters().getName(i), "");//game.getParameter(game.getParameters().getName(i)).toString());
        }

    }

    @Override
    public String toString() {
        //System.out.println("In toString " + name + "  " + );
        return name;
    }
}
