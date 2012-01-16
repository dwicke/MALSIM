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
public abstract class GameProperties extends Properties{
    protected String name;
    protected int numAgents;
    
     @Override
    public Type getPropertyType() {
        return Type.Game;
    }
    @Override
     public abstract String toString();
     public void setString(String newName)
     {
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
        this.setField("number of agents", numAgents);
        this.setFieldClass("number of agents", Integer.class);
     }
     
     @Override
    public boolean setField(String fieldAlias, Object val) {
        
        if (fieldAlias.equals("number of agents")) {
            int prev = numAgents;
            try {
                numAgents = Integer.parseInt(val.toString());
            } catch (NumberFormatException exp) {
                numAgents = prev;
                return false;
            }
        }
        super.setField(fieldAlias, val);
        return true;
     }
}
