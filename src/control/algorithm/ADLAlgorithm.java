/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import model.properties.agent.ADLProps;

/**
 * This is good
 * @author drew
 */
public class ADLAlgorithm {
    // need a table in which each row index coresponds to a state
    // the q-value represents how good the coresponding
    // action is when in a particular state.
    private double qTable[];
    
    // every p game iterations is a "state" and each row in the table
    // represents the actions taken by all the players.
    private int history[][];
    
    // the current time-step.  this is used to see where i am at in the history
    private int time = 0;
    // history length is the p value. this is the "window" that i view
    // history.  I look at chunks in history of size "historyLength" or p
    private int historyLength;
    // the number of agents playing the game
    private int numAgents;
    // the properties ie where alpha and gamma are stored
    private ADLProps props;
    // the number of actions that I can choose from
    private int numActions;
    // the action that i take
    private int action;
    // represents the index to my action in the joint actions list
    private int myIndex;
    // this is the index in the gamut game that I am it is used so that i don't need to -1 to get index
    private int myOrder;
    // the reward for the current game 
    private double reward;
    
    
    public ADLAlgorithm(ADLProps props, int numAgents, int numActions, int numReps)
    {
        this.historyLength = props.getHistLength();
        this.numAgents = numAgents;
        history = new int[numReps][numAgents];
        qTable = new double[numReps];
        this.props = props;
        this.numActions = numActions;
        action = 0;
    }
    
    /**
     * who i am in the list of actions
     * @param order 
     */
    public void setOrder(int order) {
        myIndex = order - 1;
        myOrder = order;
    }
    
    public int getAction() {
        if (action == 0 )
        {
            // this means that no agent has played yet
            // since there is not a 0 action so get a random action
            action = 1 + (int)(Math.random() * ((numActions - 1) + 1));
            
        }
        else
        {
            // means that I have already played
            // so chose an action use boltzman equation
            // to get the line 5 "Play action with some decreasing exploration"
            
        }
        // line 5 by returning the action I am playing
        return action;
    }

    /**
     * the reward for playing the action
     * @param score 
     */
     public void setScore(double score) {
        // line 6
        reward = score;
    }
     
    public void setJointAction(int[] actions) {
        
        // add the actions to the history
        if (time < history.length)
        {
            history[time] = actions;
            // move to the next time step
            time++;

            // maybe i should only search in history if windowStart is greater than 0
            // if it is then i will get a random action
            // get where my current history window starts
            // if I have not seen historyLength number of game iterations
            // then I will have to start at zero.
            int windowStart = (time - historyLength < 0) ? 0 : (time - historyLength);

            if (windowStart > 0)
            {
                int windowEnd = time - 1; // I have incremented to a time that hasn't occured yet
                int historyStart = 0;// always start here!
                // end one game iteration before the first one otherwise it will include window
                int historyEnd = time - historyLength - 1; 

                // contains the index into the history that contains the action
                // that i took in a previous history I can then use these indices
                // to look up what action i should take based on the q-values
                // of these indices
                ArrayList<Integer> matchingIndices = new ArrayList<Integer>();

                // loop over the history not in the window
                for (int i = historyStart; i <= historyEnd; i++)
                {
                    int pastIndex = i;
                    boolean match = true;
                    // loop over the window and compare to a past history window
                    // using i as the start point for the past window
                    for (int currentIndex = windowStart; currentIndex <= windowEnd; currentIndex++)
                    {
                        // so if the past action and the current action equal 
                        if (Arrays.equals(history[pastIndex], history[currentIndex]))
                        {
                            // check the next one
                            pastIndex++;
                        }
                        else
                        {
                            // they didn't match so go to the next past history window
                            match = false;
                            break;
                        }
                    }
                    if (match == true)
                    {
                        // then I should add the next index to the 
                        matchingIndices.add(pastIndex + 1);
                    }
                }

                // now i can find the action that maximizes the
                // q-value by looking up the q-value that coresponds
                // to that state
                double maxQ = 0; // this is the expected utility when i take the action i find here
                int actionIndex = -1;
                for (int i = 0; i < matchingIndices.size(); i++)
                {
                    if (i == 0 || qTable[matchingIndices.get(i)] > maxQ)
                    {
                        maxQ = qTable[matchingIndices.get(i)];
                        actionIndex = matchingIndices.get(i);
                    }
                }


                // so now i can calculate the q-value
                // for the previous action
                if (actionIndex == -1)
                {
                    // take a random action since I have never seen this history before
                    action = 0;
                    // update the previos action
                    qTable[time - 2] = props.getAlpha()* (reward);
                }
                else
                {
                    // now that I have the index of the best action i must get the action
                    // that i took in the action joint action list
                    action = history[actionIndex][myIndex];
                    qTable[time - 2] = (1 - props.getAlpha()) * qTable[matchingIndices.get(matchingIndices.size() - 1)]
                        + props.getAlpha()* (reward + props.getGamma()*maxQ);
                }



            }
            else
            {
                action = 0;
                if (time > 1)
                {
                    qTable[time - 2] = props.getAlpha()* (reward);
                }
            }
        }
        else{
            System.err.println("ERROR ADLAlgorithm joint action went out of bounds.");
        }
        
        
    }
}
