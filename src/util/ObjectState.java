/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.lang.Thread.State;
import util.BasicPublisher;
import util.Subscriber;

/**
 * This class is used to give an object a state
 * The state is also viewable.
 * @author drew
 */
public class ObjectState extends Viewable{
    private BasicPublisher pub;
    private State objState;

    public ObjectState() {
        pub = new BasicPublisher();
        
    }
    
    public ObjectState(State st) {
        pub = new BasicPublisher();
        objState = st;
    }
    /**
     * Does not notify the subs. since state was presumeably set by the sub.
     * @param st
     * @param sub 
     */
    public ObjectState(State st, Subscriber sub) {
        pub = new BasicPublisher();
        pub.addSubscriber(sub);
        objState = st;
        
    }
    
    public void addSub(Subscriber sub)
    {
        pub.addSubscriber(sub);
    }
    
    public void setState(State state)
    {
        this.objState = state;
        pub.notifySubscribers(this, this.objState);
    }
    
    public State getState()
    {
        return objState;
    }

    @Override
    public void generateViewFields() {
        setField("state", objState);
        setFieldClass("state", State.class);
        
    }
}