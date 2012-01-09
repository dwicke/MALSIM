/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import util.ObjectState;
import com.sun.net.httpserver.Authenticator.Success;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author drew
 */
public class BatchTest {
    
    public BatchTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addTournament method, of class Batch.
     */
    @Test
    public void testAddTournament() {
        System.out.println("addTournament");
        Tournament tourn = new Tournament();
        Batch instance = new Batch();
        instance.addTournament(tourn);
        // TODO review the generated test code and remove the default call to fail.
       assertEquals(instance.getTourn().size(), 1);
    }

    /**
     * Test of removeTournament method, of class Batch.
     */
    @Test
    public void testRemoveTournament() {
        System.out.println("removeTournament");
        Tournament tourn = new Tournament();
        Batch instance = new Batch();
        instance.addTournament(tourn);
        instance.removeTournament(tourn);
        assertEquals(instance.getTourn().size(), 0);
    }

    /**
     * Test of getBatch method, of class Batch.
     */
    @Test
    public void testGetBatch() {
        System.out.println("getBatch");
        Tournament tourn = new Tournament();
        Batch instance = new Batch();
        instance.addTournament(tourn);
        TreeMap<Tournament, ObjectState> result = instance.getBatch();
        assertNotNull(result.get(tourn));
        assertEquals(tourn, result.lastKey());
        
    }

    /**
     * Test of getTourn method, of class Batch.
     */
    @Test
    public void testGetTourn() {
        System.out.println("getTourn");
        Tournament tourn = new Tournament();
        Batch instance = new Batch();
        instance.addTournament(tourn);
        HashSet expResult = new HashSet();
        expResult.add(tourn);
        Set result = instance.getTourn();
        assertEquals(expResult.contains((result.toArray())[0]), true);
    }

    
}
