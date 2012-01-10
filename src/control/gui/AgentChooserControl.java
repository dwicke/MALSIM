/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import model.agent.Agent;
import model.properties.game.TournamentProperties;
import util.BasicPublisher;
import util.GenericFactory;
import util.Subscriber;

/**
 *
 * @author drew
 */
public class AgentChooserControl implements ChooserControl{
    private BasicPublisher pub;
    private GenericFactory fac;
    private TournamentProperties tournProps;
    private TreeMap<String, Agent> stringAgentMap;
    private TreeMap<String, Integer> agentCount;

    public AgentChooserControl() {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        stringAgentMap = new TreeMap<String, Agent>();
        agentCount = new TreeMap<String, Integer>();
        fac.generateMaping("config/AgentList.cfg");
    }

    AgentChooserControl(TournamentProperties tournProps) {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        fac.generateMaping("config/AgentList.cfg");
        stringAgentMap = new TreeMap<String, Agent>();
        agentCount = new TreeMap<String, Integer>();
        this.tournProps = tournProps;
    }
    
    @Override
    public void addSubscriber(Subscriber sub)
    {
        pub.addSubscriber(sub);
    }

    @Override
    public ArrayList<String> getChoices() {
        return new ArrayList<String>(fac.getSimpleRepresentation());
    }

    @Override
    public ArrayList<String> getChosen() {
        ArrayList<String> list = new ArrayList<String>();
        ArrayList<Agent> agentList = tournProps.getAgents();
        // must clear the list so that nothing old is left over
        stringAgentMap.clear();
        agentCount.clear();
        for (Agent ag : agentList)
        {
            list.add(addStringAgent(ag.toString(), ag));
            
        }
        return list;
    }

    @Override
    public String addChoice(String choice) {
        // Create a new agent from the name of the agent
        Agent newAgent = (Agent)fac.getObject(choice);
        // set the name of the agent to ensure uniqueness
        String newName = addStringAgent(choice, newAgent);
        // add the agent to the tournament
        tournProps.addAgent(newAgent);
        // return the name of the agent so that it can be displayed in the chosen list
        return newName;
    }

    @Override
    public void removeChoice(String choice) {
        // remove the agent from both the local list and the tournament
        tournProps.removeAgent(removeAgent(choice));
    }
    

    private String addStringAgent(String toString, Agent ag) {
        String newstring = toString;
        
        if (agentCount.get(newstring) == null)
        {
            agentCount.put(newstring, 1);
            ag.setID(0);
            stringAgentMap.put(newstring, ag);
            return newstring;
        }
        else
        {
            Integer count = agentCount.get(newstring);
            ag.setID(count);
            String newName = newstring + "_" + ag.getID();
            stringAgentMap.put(newName, ag);
                    
            agentCount.put(toString, count + 1);
            return newName;
        }
        
       
        
    }

    private Agent removeAgent(String choice) {
        // remove the agent from the list here and return the agent that it maped to so
        // that it can be removed from the tournament.
        return stringAgentMap.remove(choice);
    }
    
    @Override
    public String toString()
    {
        return "Agent Choices";
    }

    @Override
    public void notifyObservers(String choice) {
        pub.notifySubscribers(this, stringAgentMap.get(choice).getProperties());
    }
    
}
