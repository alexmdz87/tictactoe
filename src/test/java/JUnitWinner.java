/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.challenge.bll.ArtifitialGamer;
import com.challenge.view.GameBean;
import com.utils.StrategyUtils;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexmendez
 */
public class JUnitWinner {
    
    Integer[] boardPositions = new Integer[9];
    String[] MovWinner = {"012", "345", "678",
        "036", "147", "258",
        "048", "246"};

    public JUnitWinner() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        
        
        
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void evaluateSmartWinMove() {
        int humanPos=1;
        int machinaPos=2;
        
        GameBean game = new GameBean();
        game.getHuman().setOrderPosition(humanPos);
        game.startGame();
        
        game.mechanicMove(0);
        game.mechanicMove(6);
        game.mechanicMove(2);
        
        assertEquals((Integer)machinaPos, game.getWinner());
    }
    
    @Test
    public void evaluateSmartTie() {
        GameBean game = new GameBean();
        game.getHuman().setOrderPosition(1);
        game.startGame();
        
        game.mechanicMove(0);
        game.mechanicMove(6);
        game.mechanicMove(5);
        game.mechanicMove(7);
        game.mechanicMove(2);
        
        assertEquals(null, game.getWinner());
        assertEquals("finished", game.getState());
    }
    
}
