/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import ibis.mpj.MPJ;
import ibis.mpj.MPJException;
import ibis.mpj.Status;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.game.Tag;

/**
 *
 * @author drew
 */
public class MPIRecvOverseer implements Runnable{
    private static TreeMap<Tag, Queue<Object>> table = new TreeMap<Tag, Queue<Object>>();

    private static TreeMap<Tag, Object> hooks = new TreeMap<Tag, Object>();
    @Override
    public void run() {
        try {
            while(MPJ.initialized() == true)
            {
                System.out.println("Init in MPIRecvOverseer");
                Object[] recv = new Object[1];
                Status s = MPJ.COMM_WORLD.recv(recv, 0, 1, MPJ.OBJECT, MPJ.ANY_SOURCE, MPJ.ANY_TAG);
                Tag g = new Tag(s.getTag());
                if (table.containsKey(g))
                {
                    // means that a queue has already been made
                    // just add the object to the queue
                    table.get(g).add(recv[0]);
                    table.put(g, table.get(g));
                }
                else
                {
                    // means that i need to add a new queue
                    Queue que = new ConcurrentLinkedQueue();// new LinkedList();
                    que.add(recv[0]);
                    
                    table.put(g, que);
                    
                }
                // if there is a hook for this key then I must notify the
                // hook
                if (hooks.containsKey(g))
                {
                    System.out.println("Got KEY");
                    synchronized(hooks.get(g))
                    {
                        System.err.println("State of the " + hooks.get(g));
                        hooks.get(g).notifyAll();
                        
                    }
                }
            }
        } catch (MPJException ex) {
            Logger.getLogger(MPIRecvOverseer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static Object getNextObject(Tag fromWhere)
    {
        return (table.get(fromWhere) == null) ? null : table.get(fromWhere).remove();
    }
    
    
    public static boolean probe(Tag fromWhere)
    {
        return (table.get(fromWhere) != null) && (table.get(fromWhere).peek() != null);
    }
    
    public static void hook(Object hook, Tag needed)
    {
        System.err.println("IN HOOK");
        hooks.put(needed, hook);
    }
    
    public static void unhook(Tag tag)
    {
        System.err.println("IN unhook");
        hooks.remove(tag);
    }
    
}
