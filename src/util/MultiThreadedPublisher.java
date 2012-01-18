/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author drew
 */
public class MultiThreadedPublisher implements Publisher{

    ArrayList<MultiThreadedSubscriber> sub = new ArrayList<MultiThreadedSubscriber>();
    
    @Override
    public void addSubscriber(Subscriber s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeSubscriber(Subscriber s) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void removeAllSubscribers() throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
