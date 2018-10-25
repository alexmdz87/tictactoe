/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.challenge.bll.ArtifitialGamer;
import com.utils.StrategyUtils;
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
public class JUnitSmartFirstReturnMov {

    public JUnitSmartFirstReturnMov() {
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
    public void testBestFirstReturnMoveToCenterTaken() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(2);
        Integer[] boardPositions = new Integer[9];
        boardPositions[4]=1;
        Collections.addAll(machine.getExpectedGoals(), StrategyUtils.MovWinner);
        
        assertEquals(0, machine.smartMove(boardPositions));
    }
    
    @Test
    public void testBestFirstReturnMoveToCornerTaken() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(2);
        Integer[] boardPositions = new Integer[9];
        boardPositions[0]=1;
        Collections.addAll(machine.getExpectedGoals(), StrategyUtils.MovWinner);
        
        assertEquals(4, machine.smartMove(boardPositions));
    }
    
    @Test
    public void testBestFirstReturnMoveToSidesTaken() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(2);
        Integer[] boardPositions = new Integer[9];
        boardPositions[1]=1;
        Collections.addAll(machine.getExpectedGoals(), StrategyUtils.MovWinner);
        
        assertEquals(4, machine.smartMove(boardPositions));
    }
    
}
