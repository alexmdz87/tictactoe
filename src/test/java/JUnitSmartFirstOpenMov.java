/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.challenge.bll.ArtifitialGamer;
import com.utils.StrategyUtils;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alexmendez
 */
public class JUnitSmartFirstOpenMov {

    public JUnitSmartFirstOpenMov() {
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
    
    @Test
    public void evaluatePositiveMoveByPosition() {
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        
        int[] bestPositiveMoveByPosition={3,2,3,2,4,2,3,2,3};
        int[] positiveMoves = new int[9];
        
        for(int i=0; i<9; i++){
            positiveMoves[i]=StrategyUtils.findMovesByPosition (i, goals, null, true);
        }
        
        Assert.assertArrayEquals(bestPositiveMoveByPosition, positiveMoves);
    }
    
    @Test
    public void evaluateBlockingMoveByPosition() {
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        
        int[] bestPositiveMoveByPosition={0,0,0,0,0,0,0,0,0};
        int[] positiveMoves = new int[9];
        
        for(int i=0; i<9; i++){
            positiveMoves[i]=StrategyUtils.findMovesByPosition (i, goals, null, false);
        }
        
        Assert.assertArrayEquals(bestPositiveMoveByPosition, positiveMoves);
    }

    @Test
    public void testBestFirstOpenMove() {
        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(1);
        Integer[] boardPositions = new Integer[9];
        Collections.addAll(machine.getExpectedGoals(), StrategyUtils.MovWinner);

        assertEquals(4, machine.smartMove(boardPositions));
    }

}
