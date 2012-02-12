/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model.game;

import java.lang.Thread.State;
import java.util.ArrayList;
import model.agent.Agent;
import model.agent.QLearningAgent;
import model.properties.game.GameProperties;
import model.properties.game.RepeatedGameProperties;
import model.properties.game.TournamentProperties;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.GameFactory;
import static org.junit.Assert.*;
import util.GenericFactory;
import util.ObjectState;

/**
 *
 * @author drew
 */
public class TournamentTest {
    
    public TournamentTest() {
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
     * Test of startTourn method, of class Tournament.
     */
    @Test
    public void testStartTourn() {
        System.out.println("startTourn");
         Tournament instance = new Tournament();
        TournamentProperties props = new TournamentProperties();
        props.setMaxNumThreads(1);
        props.addAgent(new QLearningAgent());
        props.addAgent(new QLearningAgent());
        
        StandardAgentSelector sel = new StandardAgentSelector();
        StandardEliminator el = new StandardEliminator();
        props.setAgentSelector(sel);
        props.setEliminatorMethod(el);
        
        GameFactory fac = new GameFactory();
        fac.generateMaping();
        Game g = fac.getObject("PrisonersDilemma");
        g.getGameProps().generateViewFields();
        
        
        props.setGameProps(g.getGameProps());
        ((RepeatedGameProperties)g.getGameProps()).setNumReps(1);
        instance.setTournProps(props);
        instance.setState(new ObjectState(State.RUNNABLE));
        instance.getState().setState(State.RUNNABLE);
        
        
        instance.setupTourn();
        instance.startTourn();
        
        assertTrue(sel.nextContestants().isEmpty());
    }
    

}