/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.challenge.bll.ArtifitialGamer;
import com.utils.StrategyUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexmendez
 */
public class JUnitSmartThirdOpenMov {

    public JUnitSmartThirdOpenMov() {
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
    public void evaluatePriorWinningMove() {
        ArrayList<Integer> myPos = new ArrayList<>();
        myPos.add(1);
        myPos.add(4);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);

        int pos=StrategyUtils.findPositivePrior(goals, myPos);
        assertEquals(7, pos);
    }
    
    @Test
    public void evaluatePriorBlockingMove() {
        ArrayList<Integer> enemyPos = new ArrayList<>();
        enemyPos.add(0);
        enemyPos.add(3);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);

        int pos=StrategyUtils.findPositivePrior(goals, enemyPos);
        assertEquals(6, pos);
    }
    
    @Test
    public void evaluateSmartPriorWinMove() {
        int enemy=2;
        int ia=1;
        //Positions already taken
        Integer[] boardPositions={enemy,ia,null,enemy,ia,null,null,null,null};
        ArrayList<Integer> myPos = new ArrayList<>();
        myPos.add(1);
        myPos.add(4);

        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(ia);
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        machine.setExpectedGoals(goals);
        machine.setEarnedPositions(StrategyUtils.findEnemyPos(boardPositions, enemy));
        
        assertEquals(7, machine.smartMove(boardPositions));
    }
    
    
}
