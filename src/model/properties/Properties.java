/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties;

import util.Viewable;

/**
 *
 * @author drew
 */
public abstract class Properties extends Viewable{
    public Properties()
    {
        super();
    }
    public abstract Type getPropertyType();
}
