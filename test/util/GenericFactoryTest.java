/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import model.agent.QLearningAgent;
import java.util.Set;
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
public class GenericFactoryTest {
    
    public GenericFactoryTest() {
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
     * Test of generateMaping method, of class GenericFactory.
     */
    @Test
    public void testGenerateMaping() {
        System.out.println("generateMaping");
        String file = "config/AgentList.cfg";
        GenericFactory instance = new GenericFactory();
        assertEquals(instance.generateMaping(file), true);
        
    }

    /**
     * Test of getSimpleRepresentation method, of class GenericFactory.
     */
    @Test
    public void testGetSimpleRepresentation() {
        System.out.println("getSimpleRepresentation");
        String file = "config/AgentList.cfg";
        GenericFactory instance = new GenericFactory();
        assertEquals(instance.generateMaping(file), true);
        Set<String> simple = instance.getSimpleRepresentation();
        assertEquals(simple.toArray()[0], "QLearning");
    }

    /**
     * Test of getObject method, of class GenericFactory.
     */
    @Test
    public void testGetObject() {
        System.out.println("getObject");
        String file = "config/AgentList.cfg";
        GenericFactory instance = new GenericFactory();
        assertEquals(instance.generateMaping(file), true);
        Object obj = instance.getObject("QLearning");
        assertTrue(obj instanceof QLearningAgent);
        
    }
}
