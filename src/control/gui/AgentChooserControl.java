/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;
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

    public AgentChooserControl() {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        stringAgentMap = new TreeMap<String, Agent>();
        fac.generateMaping("config/AgentList.cfg");
    }

    AgentChooserControl(TournamentProperties tournProps) {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        fac.generateMaping("config/AgentList.cfg");
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
        for (Agent ag : tournProps.getAgents())
        {
            list.add(addStringAgent(ag.toString(), ag));
            
        }
        return list;
    }

    @Override
    public void addChoice(String choice) {
        tournProps.addAgent((Agent)fac.getObject(choice));
    }

    @Override
    public void removeChoice(String choice) {
        tournProps.removeAgent(removeAgent(choice));
    }

    private String addStringAgent(String toString, Agent ag) {
        String newstring = toString;
        if (stringAgentMap.containsKey(toString))
        {
            if (Character.isDigit(toString.charAt(toString.length() - 1)) == true)
            {
                // increase the last digit by 1 to make different
                newstring = newstring.subSequence(0, newstring.length() - 1) + Integer.toString(Character.digit(newstring.charAt(toString.length() - 1) + 1,10));
            }
        }
        stringAgentMap.put(newstring, ag);
        return newstring;
    }

    private Agent removeAgent(String choice) {
        return stringAgentMap.remove(choice);
    }
    
}
