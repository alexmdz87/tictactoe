/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.challenge.bll.ArtifitialGamer;
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
public class JUnitTest {
    
    Integer[] boardPositions = new Integer[9];
    String[] MovWinner = {"012", "345", "678",
        "036", "147", "258",
        "048", "246"};

    public JUnitTest() {
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
    public void testFirtsMove() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(1);
        Collections.addAll(machine.getExpectedGoals(), MovWinner);
        
        assertEquals(4, machine.smartMove(boardPositions));
    }
    
    @Test
    public void testSecondMove() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(1);
        Collections.addAll(machine.getExpectedGoals(), MovWinner);
        
        assertEquals(4, machine.smartMove(boardPositions));
        assertEquals(0, machine.smartMove(boardPositions));
    }
    
    @Test
    public void testTrickyMove() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(1);
        Collections.addAll(machine.getExpectedGoals(), MovWinner);
        
        assertEquals(4, machine.smartMove(boardPositions));
        assertEquals(0, machine.smartMove(boardPositions));
        assertEquals(2, machine.smartMove(boardPositions));
    }
    
}
