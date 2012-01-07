/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.properties.agent;

import model.properties.Properties;
import model.properties.Type;

/**
 *
 * @author drew
 */
public abstract class AgentProperties extends Properties{

    public AgentProperties()
    {
        super();
    }
    @Override
    public Type getPropertyType() {
        return Type.Agent;
    }

    
}
