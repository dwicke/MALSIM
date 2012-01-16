/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.game;

import model.properties.Properties;
import model.properties.Type;

/**
 *
 * @author drew
 */
public abstract class GameProperties extends Properties {

    protected String name;
    protected int numAgents;

    @Override
    public Type getPropertyType() {
        return Type.Game;
    }

    @Override
    public abstract String toString();

    public void setString(String newName) {
        name = newName;
    }

    public int getNumAgents() {
        return numAgents;
    }

    public void setNumAgents(int numAgents) {
        this.numAgents = numAgents;
    }

    /**
     * Essentially this method will be called
     * to get the game data ready.
     */
    //  public abstract void initGameData();
    @Override
    public void generateViewFields() {
        this.fieldVals.put("number of agents", numAgents);
        this.fieldAlias.put("number of agents", Integer.class);
    }

    public double parseDouble(Object val, double orig) {
        String hold = val.toString();
        double newDouble = 0;
        try {
            newDouble = Double.parseDouble(hold);
        } catch (NumberFormatException exp) {
            newDouble = orig;
        }
        return newDouble;
    }

    @Override
    public boolean setField(String fieldAlias, Object val) {

        if (fieldAlias.equals("number of agents")) {
                numAgents = (int) parseDouble(val, numAgents);//Integer.parseInt(val.toString());
                this.fieldVals.put(fieldAlias, numAgents);
                return true;
        }

        return false;

    }
}
