/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import ibis.mpj.MPJ;
import ibis.mpj.MPJException;

/**
 *
 * @author drew
 */
public class TournamentFactory {
    public static Tournament getTournament() throws MPJException
    {
        if (MPJ.initialized() == true)
        {
            return new MPITourn();
        }
        else
        {
            return new Tournament();
        }
    }
    
}
