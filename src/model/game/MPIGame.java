/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.rmi.RemoteException;
import ibis.mpj.MPJ;
/**
 *
 * @author drew
 */
public class MPIGame extends Game{

    public static void main(String args[])
    {
        
        
        // first of all I think that this should be in the
        // Game class next I think that I need to have the 
        // MPJ initialize in the beginning of the gui
        // then when a user says in the properties that they 
        // want to use MPJ when the tournament creates a game
        // it calls startGame in order to start a new 
        // proccess that will run the actual game which is "this"
        
        // to do so I will send it an integer tag in the args which will
        // be the way that I will be able to know that 
        // i am communicating with the proc that I started
        // I will need to use probe in order to be able
        // work with other threads. since I don't want to
        // ignore messages that other threads might want.
        
        // The new proc will message me with its rank
        // then I will send it "this" so that it can create
        // itself and start running
        
        
        
        // so here I will need to do MPJ initialize etc
        // so that I can tell 
        
    }
    
    @Override
    public void startGame() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Object pub, Object code) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
