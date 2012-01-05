/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import ma2.Index;
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
public class GameMatrixTest {
    
    public GameMatrixTest() {
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
     * Test of setValue method, of class GameMatrix.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        int shape[] = {0,0};
        int game[] = {2,2};
        Index loc = Index.factory(shape);
        Object value = 3.2;
        GameMatrix instance = new GameMatrix(game, Double.class);
        instance.setValue(loc, value);
        assertEquals(value, instance.getValue(loc));
        System.out.println("value " + value + " = " + instance.getValue(loc));
    }

    
}
