/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

/**
 *
 * @author drew
 */
public class Tag {
    private int tag;
    private boolean used;
    private final Object mutex = new Object();
    
    public Tag(int tag)
    {
        this.tag = tag;
        used = false;
    }
    public boolean useTag()
    {
        synchronized(mutex)
        {
            if (used == false)
            {
                used = true;
                return true;
            }
            return false;
        }
        
    }
    public void setFinished()
    {
        synchronized(mutex)
        {
            used = false;
        }
    }
    public int getTag()
    {
        return tag;
    }
}
