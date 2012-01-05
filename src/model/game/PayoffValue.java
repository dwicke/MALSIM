/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

/**
 * This is a template class that is used to define a
 * payoff value.  It is generic so it can be used
 * anywhere.
 * @author drew
 */
public interface PayoffValue {
    
    public Object getValue();
    public void setValue(Object val);
}
