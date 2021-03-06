/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.thoughtworks.xstream.annotations.XStreamOmitField;
import java.lang.Thread.State;
import util.BasicPublisher;
import util.Subscriber;

/**
 * This class is used to give an object a state
 * The state is also viewable.
 * @author drew
 */
public class ObjectState extends Viewable{
    @XStreamOmitField
    private BasicPublisher pub;
    private State objState;
    private Object code;

    public ObjectState() {
        pub = new BasicPublisher();
        
    }
    
    public ObjectState(State st) {
        pub = new BasicPublisher();
        objState = st;
    }
    
    private Object readResolve() {
        pub = new BasicPublisher();
        return this;
    }
    /**
     * Does not notify the subs. since state was presumeably set by the sub.
     * code is the code that is set to when pub is called and this ObjectState is
     * what is set to pub
     * @param st
     * @param sub 
     */
    public ObjectState(State st, Subscriber sub, Object code) {
        pub = new BasicPublisher();
        pub.addSubscriber(sub);
        this.code = code;
        objState = st;
        
    }
    
    public void addSub(Subscriber sub)
    {
        pub.addSubscriber(sub);
    }
    
    public void removeSub(Subscriber sub)
    {
        pub.removeSubscriber(sub);
    }
    
    public void setState(State state)
    {
        this.objState = state;
        if (state == State.TERMINATED)
        {
            System.out.println("Setting State to term");
        }
        
        System.out.println("The pub is " + pub);
        pub.notifySubscribers(this, code);
        
        if (state == State.TERMINATED)
        {
            System.out.println("Notified");
        }
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
