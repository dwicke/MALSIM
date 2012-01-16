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
public class QLearningAgent extends MatrixAgent{

     
     // fields that will bind to the 
     // once i know how to implement qlearning

    public QLearningAgent() {
        super();
        this.setProperties(new QLearningAgentProperties());
    }
    
    @Override
    public void takeTurn() {
        
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

    @Override
    public int compareTo(Agent o) {
        return (this.toString() + this.getID()).compareTo((o.toString() + o.getID()));
    }
    
}
