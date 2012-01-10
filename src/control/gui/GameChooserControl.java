/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.util.ArrayList;
import model.game.Game;
import model.properties.game.GameProperties;
import model.properties.game.TournamentProperties;
import util.BasicPublisher;
import util.GenericFactory;
import util.Subscriber;

/**
 *
 * @author drew
 */
public class GameChooserControl implements ChooserControl {

    private TournamentProperties tournProps;
    private BasicPublisher pub;
    private GenericFactory fac;

    public GameChooserControl(TournamentProperties props) {
        pub = new BasicPublisher();
        fac = new GenericFactory();
        fac.generateMaping("config/GameList.cfg");
        tournProps = props;
    }

    /**
     * Sets the gameproperties for the tournament
     * @param gameProps 
     */
    public void setGameProps(GameProperties gameProps) {
        tournProps.setGameProps(gameProps);
    }

    public GameProperties getGameProperties() {
        return tournProps.getGameProps();
    }

    @Override
    public ArrayList<String> getChoices() {
        return new ArrayList<String>(fac.getSimpleRepresentation());
    }

    @Override
    public ArrayList<String> getChosen() {
        ArrayList<String> list = new ArrayList<String>();
        if (tournProps.getGameProps() != null) {
            list.add(tournProps.getGameProps().toString());
        }
        return list;
    }

    @Override
    public String addChoice(String choice) {
        tournProps.setGameProps(((Game) fac.getObject(choice)).getGameProps());
        return choice;
    }

    @Override
    public void removeChoice(String choice) {
        tournProps.setGameProps(null);
    }

    @Override
    public void addSubscriber(Subscriber sub) {
        pub.addSubscriber(sub);
    }

    @Override
    public String toString() {
        return "Game Choices";
    }

    @Override
    public void notifyObservers(String choice) {
        pub.notifySubscribers(this, tournProps.getGameProps());
    }
}
