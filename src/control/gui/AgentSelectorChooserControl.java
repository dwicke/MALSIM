/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.util.ArrayList;
import java.util.TreeMap;
import model.agent.Agent;
import model.game.AgentSelector;
import model.game.StandardAgentSelector;
import model.properties.game.TournamentProperties;
import util.BasicPublisher;
import util.GenericFactory;
import util.Subscriber;

/**
 *
 * @author drew
 */
public class AgentSelectorChooserControl implements ChooserControl{

    private BasicPublisher pub;
    private GenericFactory fac;
    private TournamentProperties tournProps;
    private TreeMap<String, Agent> stringAgentMap;
    private TreeMap<String, Integer> agentCount;

    public AgentSelectorChooserControl() {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        stringAgentMap = new TreeMap<String, Agent>();
        agentCount = new TreeMap<String, Integer>();
        fac.generateMaping("config/AgentSeletorList.cfg");
    }

    AgentSelectorChooserControl(TournamentProperties tournProps) {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        fac.generateMaping("config/AgentSelectorList.cfg");
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
        if (tournProps.getAgentSelector() != null)
        {
            list.add(tournProps.getAgentSelector().toString());
        }
        return list;
    }

    @Override
    public String addChoice(String choice) {
        tournProps.setAgentSelector(((AgentSelector) fac.getObject(choice)));
        return choice;
    }

    @Override
    public void removeChoice(String choice) {
        // must always have some type of eliminator so set to the standard
        tournProps.setAgentSelector(new StandardAgentSelector());
    }

    @Override
    public void notifyObservers(String choice) {
        this.pub.notifySubscribers(this, null);// no properties object for eliminators
        // in the future if I need to have a properties object then I will need to
        // publish the eliminator's properties object
    }
    @Override
    public String toString() {
        return "Agent Selector Choices";
    }
    
}
