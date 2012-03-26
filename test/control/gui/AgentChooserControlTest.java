/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package control.gui;

import info.monitorenter.gui.chart.ITrace2D;
import java.util.ArrayList;
import model.game.Batch;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import util.AgentDataCollector;
import util.Subscriber;

/**
 *
 * @author drew
 */
public class AgentChooserControlTest {
    
    public AgentChooserControlTest() {
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
     * Test of addSubscriber method, of class AgentChooserControl.
     */
    @Test
    public void testAddSubscriber() {
        System.out.println("addSubscriber");
        Subscriber sub = new Batch();
        AgentChooserControl instance = new AgentChooserControl();
        instance.addSubscriber(sub);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getChoices method, of class AgentChooserControl.
     */
    @Test
    public void testGetChoices() {
        System.out.println("getChoices");
        AgentChooserControl instance = new AgentChooserControl();
        ArrayList expResult = null;
        ArrayList result = instance.getChoices();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getChosen method, of class AgentChooserControl.
     */
    @Test
    public void testGetChosen() {
        System.out.println("getChosen");
        AgentChooserControl instance = new AgentChooserControl();
        ArrayList expResult = null;
        ArrayList result = instance.getChosen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTraceNames method, of class AgentChooserControl.
     */
    @Test
    public void testGetTraceNames() {
        System.out.println("getTraceNames");
        String agent = "";
        AgentChooserControl instance = new AgentChooserControl();
        ArrayList expResult = null;
        ArrayList result = instance.getTraceNames(agent);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAgentDataCollector method, of class AgentChooserControl.
     */
    @Test
    public void testGetAgentDataCollector() {
        System.out.println("getAgentDataCollector");
        String agent = "";
        String sTrace = "";
        ITrace2D trace = null;
        AgentChooserControl instance = new AgentChooserControl();
        AgentDataCollector expResult = null;
        AgentDataCollector result = instance.getAgentDataCollector(agent, sTrace, trace);
        assertEquals(expResult, result);
    }

    /**
     * Test of addChoice method, of class AgentChooserControl.
     */
    @Test
    public void testAddChoice() {
        System.out.println("addChoice");
        String choice = "";
        AgentChooserControl instance = new AgentChooserControl();
        String expResult = "";
        String result = instance.addChoice(choice);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeChoice method, of class AgentChooserControl.
     */
    @Test
    public void testRemoveChoice() {
        System.out.println("removeChoice");
        String choice = "";
        AgentChooserControl instance = new AgentChooserControl();
        instance.removeChoice(choice);
    }
    
}
