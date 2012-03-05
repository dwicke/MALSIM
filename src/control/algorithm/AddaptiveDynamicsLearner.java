/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.algorithm;

import model.properties.agent.ADLProps;
import util.MersenneTwister;

/**
 *
 * @author drew
 */
public class AddaptiveDynamicsLearner {
    
    // represents the index to my action in the joint actions list
    int myIndex;
    // the index of the action I am going to take
    int action;
    // the reward for the action
    double reward;
    // the number of agents playing the game
    int numAgents;
    // the history of the past p games
    int history[][];
    // how far back do i want to record the joint actions
    int historyLength;
    // current location in the history
    int histIndex;
    // the adl properties that store things like the history length
    // the alpha and gamma values for the q-learning function
    ADLProps props;
    // this is the index in the gamut game that I am it is used so that i don't need to -1 to get index
    private int myOrder;
    // get the number of actions available to the agent
    // the agent then can chose an action 1 - numActions
    // to take.
    int numActions;
    // q values that corespond to the history
    double qVals[];
    
    
    public AddaptiveDynamicsLearner(ADLProps props, int numAgents, int numActions)
    {
        this.historyLength = props.getHistLength();
        this.numAgents = numAgents;
        history = new int[historyLength][numAgents];
        qVals = new double[historyLength];
        histIndex = 0;
        this.props = props;
        this.numActions = numActions;
        action = 0;
    }
    

    public void setOrder(int order) {
        myIndex = order - 1;
        myOrder = order;
    }

    public int getAction() {
        if (action == 0 || Math.random() < 0.2)
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

    public void setScore(double score) {
        // line 6
        reward = score;
    }

    public void setJointAction(int[] actions) {
        // line 7
        history[histIndex] = actions;
        
        // continue on line 8
        // check to see if I have already visited this state
        int QIndex = -1;
        for (int i = 0; i < historyLength; i++)
        {
               if (i != histIndex)
               {
                   boolean hasVisited = true;
                   for (int j = 0; j < numActions; j++)
                   {
                       // check to see if my current state is the same as a past
                       // state
                       if (history[i][j] != history[histIndex][j])
                       {
                           hasVisited = false;
                           break;
                       }
                   }
                   if (hasVisited == true)
                   {
                       QIndex = i;
                       break;
                   }
               }
        }
        
        if (QIndex == -1)
        {
            // this is the "make a new q-value
            QIndex = histIndex;
            qVals[QIndex] = 0;// reset it
        }
        
        // then find the action that has the highest q-value
        int maxQIndex = 0;
        for(int i = 0; i < historyLength; i++)
        {
            if (qVals[maxQIndex] < qVals[i])
            {
                maxQIndex = i;
            }
        }
        // so now I can get the best action!
        // if it is 0 then when the agent gets the
        // the action a random action will be selected
        action = history[maxQIndex][myIndex];
        
        // get the utility of chosing this
        // action
        double utility = qVals[maxQIndex];
        qVals[maxQIndex] = (1 - props.getAlpha())*qVals[maxQIndex] + 
                props.getAlpha()*(reward + props.getGamma()*utility);
        
        
        // must circle back around once i hit the end
        histIndex++;
        if(histIndex == historyLength)
        {
            histIndex = 0;
        }
        
    }
    
    
}
