/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import java.util.ArrayList;
import java.util.Set;
import util.Subscriber;

/**
 *
 * @author drew
 */
public interface ChooserControl {
    public ArrayList<String> getChoices();
    public ArrayList<String> getChosen();
    public String addChoice(String choice);
    public void removeChoice(String choice);
    public void addSubscriber(Subscriber sub);
    public void notifyObservers(String choice);
    @Override
    public String toString();
            
}
