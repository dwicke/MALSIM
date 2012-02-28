/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agent;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.Map;
import java.util.TreeMap;
import model.properties.agent.QLearningAgentProperties;

/**
 *
 * @author drew
 */
@XStreamAlias("QLearning_Agent")
public class QLearningAgent extends GamutAgent{

     
     // fields that will bind to the 
     // once i know how to implement qlearning

    public QLearningAgent() {
        super();
        this.setProperties(new QLearningAgentProperties());
    }
    
    @Override
    public void takeTurn() {
        int outcome[] = {1,1};
        int outcome1[] = {1,2};
        int outcome2[] = {2, 1};
        int outcome3[] = {2, 2};
        //System.out.println("Agent " + order + "  " + game.getPayoff(outcome) + ", " + game.getPayoff(outcome1) + "\n"  + ""
          //      + game.getPayoff(outcome2) + ", " + game.getPayoff(outcome3));
        action = 1;
    }

    @Override
    public String toString() {
        return "QLearning";
    }

    public void run() {
        takeTurn();
    }

    @Override
    public void generateViewFields() {
        
    }

    
    
}
