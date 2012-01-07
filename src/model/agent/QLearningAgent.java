/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.agent;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author drew
 */
@XStreamAlias("QLearning_Agent")
public class QLearningAgent extends Agent{

     
     // fields that will bind to the 
     // once i know how to implement qlearning

    public QLearningAgent() {
        super();
        
    }
    
    @Override
    public void takeTurn() {
        
    }

    @Override
    public String toString() {
        return "QLearning_Agent";
    }

    public void run() {
        takeTurn();
    }

    @Override
    public void generateViewFields() {
        
    }
    
}
