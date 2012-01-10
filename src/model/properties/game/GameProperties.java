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
    private String name;
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
}
