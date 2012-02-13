/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import ibis.io.IbisSerializationInputStream;
import ibis.mpj.Datatype;
import ibis.mpj.MPJ;
import ibis.mpj.MPJException;
import ibis.mpj.Status;
import java.io.IOException;
import java.rmi.RemoteException;
import util.ObjectState;
import java.lang.Thread.State;

/**
 *
 * @author drew
 */
public class MPIGameClient extends Game {

    public static void main(String args[]) throws MPJException, IOException, ClassNotFoundException {
        boolean finished = false;
        Game game = null;
        int rank = MPJ.COMM_WORLD.rank();
        // in a loop while I haven't recieved a finished command like I should fin
        while (finished == false) {
            // Here I will wait for the Game object
            // to be sent to me
            Object[] recvObject = new Object[1];// the object that will be set to the recieved game
            // /* don't really need to probe just recv*/Status recvStatus = MPJ.COMM_WORLD.probe(0, rank); // probe for a message from root for me
            MPJ.COMM_WORLD.recv(recvObject, 0, 1, MPJ.OBJECT, 0, rank); // recieve the message

            if (recvObject != null && recvObject[0] instanceof Game) {
                game = (Game) recvObject[0];

                // start the game
                ObjectState state = new ObjectState(State.RUNNABLE);
                game.setObjectState(state);
                Thread thread = new Thread(game);
                thread.start();
                boolean shouldContinue = true;
                while (shouldContinue == true) {
                    // so I will wait until either the game state changes or i get a message
                    while (state.getState() == State.RUNNABLE && MPJ.COMM_WORLD.iprobe(0, rank) == null) {
                    }

                    // so something happened find out what
                    if (state.getState() == State.TERMINATED) {
                        // then the game is finished
                        // I will send the game back to MALSIM
                        // just resuse the recvObject since it is the game
                        MPJ.COMM_WORLD.send(recvObject, 0, 1, MPJ.OBJECT, 0, rank);
                        // set the game to null to return to initial state
                        game = null;
                        shouldContinue = false;
                        // I will loop back and wait for the next game object
                    } else if (MPJ.COMM_WORLD.iprobe(0, rank) != null) {
                        // Then MALSIM sent me a message
                        // The only type of message that it can be
                        // is an ObjectState to tell me to Pause or
                        // Terminate the game
                        
                        Object[] st = new Object[1];
                        MPJ.COMM_WORLD.recv(st, 0, 1, MPJ.OBJECT, 0, rank);
                        
                        ObjectState recvState = (ObjectState) st[0];
                        if (recvState.getState() == State.WAITING)
                        {
                            // Should pause the game
                            state.setState(State.WAITING);
                            // once I recieve a pause i will wait for another 
                            // message to either terminate
                            MPJ.COMM_WORLD.recv(st, 0, 1, MPJ.OBJECT, 0, rank);
                            recvState = (ObjectState) st[0];
                            
                            // I have recieved a new Message
                            if (recvState.getState() == State.RUNNABLE)
                            {
                                // Then I must restart the Game
                                synchronized(game)
                                {
                                    game.notify();
                                }
                            }
                        }
                        // then the tournament wants the game to stop
                        // so stop it and 
                        if (recvState.getState() == State.TERMINATED)
                        {
                           
                            // I must term the thread
                            if(state.getState() == State.WAITING)
                            {
                                // so Set to terminated and
                                state.setState(State.TERMINATED);
                                // then notify that 
                                synchronized(game)
                                {
                                    game.notify();
                                }
                                
                            }
                            else
                            {
                                // term the game
                                state.setState(State.TERMINATED);
                            }
                            game = null;
                            shouldContinue = false;
                        }
                       
                    }
                }
            } else if (recvObject != null && recvObject[0] instanceof ObjectState) {
                // So if I have never recieved a Game object and the first thing
                // I recieve is an ObjectState that says TERMINATED then that
                // means that the user never started the Batch and just closed MALSIM
                // and I need to call finish on the MPJ
                if (game == null && ((ObjectState) recvObject[0]).getState() == State.TERMINATED) {
                    finished = true;
                    break;
                }
                /*// don't need to worry about this here since game will be null
                // If I have a game and it is terminated then this means that I should
                // call finish on MPJ
                else if (game != null && game.getGameState().getState() == State.TERMINATED)
                {
                finished = true;
                break;
                }
                
                 */

            }





            // and I will start it in a new thread
            // I will set up an irecv message to recieve a status object
            // and then in a busy loop i will loop until i get the status
            // object from the main proc in which case I will set to the
            // game properties also depending on the status of the 
        }
        // quit MPJ
        MPJ.finish();
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
