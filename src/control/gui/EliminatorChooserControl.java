/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.util.ArrayList;
import model.game.Eliminator;
import model.game.StandardEliminator;
import model.properties.game.TournamentProperties;
import util.BasicPublisher;
import util.GenericFactory;
import util.Subscriber;

/**
 *
 * @author drew
 */
public class EliminatorChooserControl implements ChooserControl {
    
    private TournamentProperties tournProps;
    private BasicPublisher pub;
    private GenericFactory fac;
    public EliminatorChooserControl(TournamentProperties props)
    {
         pub = new BasicPublisher();
        fac = new GenericFactory();
        fac.generateMaping("config/EliminatorList.cfg");
        tournProps = props;
    }
    
    public void setEliminator(Eliminator elim)
    {
        this.tournProps.setEliminatorMethod(elim);
    }
    
    public Eliminator getEliminator()
    {
        return this.tournProps.getEliminator();
    }

    @Override
    public ArrayList<String> getChoices() {
        return new ArrayList<String>(fac.getSimpleRepresentation());
    }

    @Override
    public ArrayList<String> getChosen() {
        ArrayList<String> list = new ArrayList<String>();
        if (tournProps.getEliminator() != null)
        {
            list.add(tournProps.getEliminator().toString());
        }
        return list;
    }

    @Override
    public String addChoice(String choice) {
        tournProps.setEliminatorMethod(((Eliminator) fac.getObject(choice)));
        return choice;
    }

    @Override
    public void removeChoice(String choice) {
        // must always have some type of eliminator so set to the standard
        tournProps.setEliminatorMethod(new StandardEliminator());
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        pub.addSubscriber(sub);
    }

    @Override
    public void notifyObservers(String choice) {
        this.pub.notifySubscribers(this, null);// no properties object for eliminators
        // in the future if I need to have a properties object then I will need to
        // publish the eliminator's properties object
    }
    @Override
    public String toString() {
        return "Eliminator Choices";
    }
    
            
}
