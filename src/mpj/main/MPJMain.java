/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpj.main;

import ibis.mpj.MPJ;
import ibis.mpj.MPJException;
import java.io.IOException;

/**
 *
 * @author drew
 */
public class MPJMain {
    public static void main(String args[]) throws MPJException, IOException, ClassNotFoundException
    {
        int size, rank;
        MPJ.init(args);
        
        size = MPJ.COMM_WORLD.size();
        rank = MPJ.COMM_WORLD.rank();
        
        if (rank == 0)
        {
            view.gui.MainFrame.main(args);
        }
        else
        {
            // Start the MPIGameClient
            model.game.MPIGameClient.main(args);
        }
    }
}
