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
public class JUnitSmartSecondReturnMov {

    public JUnitSmartSecondReturnMov() {
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
        int enemy=1;
        int ia=2;
        //Positions already taken
        Integer[] boardPositions={enemy,null,null,null,ia,null,enemy,null,null};

        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(ia);
        
        ArrayList<Integer> availablePos = StrategyUtils.findEmptyPos(boardPositions);
        ArrayList<Integer> enemyPos = StrategyUtils.findEnemyPos(boardPositions,ia);
        ArrayList<Integer> myPositions = StrategyUtils.findEnemyPos(boardPositions,enemy);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        ArrayList<String> goalForEnemy=StrategyUtils.filterTakenGoals(goals, enemyPos);
        StrategyUtils.filterTakenGoals(goalForEnemy, myPositions);
        
        int[] bestPositiveMoveByPosition={2,1,2,3,2,1};
        int[] positiveMoves = new int[availablePos.size()];        

        for (int i = 0; i < availablePos.size(); i++) {
            Integer positionToeval = availablePos.get(i);

            //Contar en cuantos mov ganadores esta esta posicion
            int freq = StrategyUtils.findMovesByPosition(positionToeval,goals,myPositions, true);

            positiveMoves[i]=freq;
        }
        
        Assert.assertArrayEquals(bestPositiveMoveByPosition, positiveMoves);
    }
    
    @Test
    public void evaluateBlockingMoveByPosition() {
        int enemy=1;
        int ia=2;
        //Positions already taken
        Integer[] boardPositions={enemy,null,null,null,ia,null,enemy,null,null};

        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(ia);
        
        ArrayList<Integer> availablePos = StrategyUtils.findEmptyPos(boardPositions);
        ArrayList<Integer> enemyPos = StrategyUtils.findEnemyPos(boardPositions,ia);
        ArrayList<Integer> myPositions = StrategyUtils.findEnemyPos(boardPositions,enemy);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        ArrayList<String> goalForEnemy=StrategyUtils.filterTakenGoals(goals, enemyPos);
        StrategyUtils.filterTakenGoals(goalForEnemy, myPositions);
        
        int[] bestBlockingMoveByPosition={1,1,2,0,1,1};
        int[] positiveMoves = new int[availablePos.size()];        

        for (int i = 0; i < availablePos.size(); i++) {

            Integer positionToeval = availablePos.get(i);

            //Contar en cuantos mov ganadores esta esta posicion
            int freq = StrategyUtils.findMovesByPosition(positionToeval,goalForEnemy,enemyPos, false);

            positiveMoves[i]=freq;

        }
        
        Assert.assertArrayEquals(bestBlockingMoveByPosition, positiveMoves);
    }
    
    @Test
    public void evaluatePriorBlockingMove() {
        int enemy=1;
        int ia=2;
        Integer[] boardPositions={enemy,null,null,null,ia,null,enemy,null,null};
        ArrayList<Integer> enemyPos = StrategyUtils.findEnemyPos(boardPositions,ia);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);

        int pos=StrategyUtils.findPositivePrior(goals, enemyPos);
        assertEquals(3, pos);
    }
    
    @Test
    public void evaluateSmartPriorBlockingMove() {
        int enemy=1;
        int ia=2;
        //Positions already taken
        Integer[] boardPositions={enemy,null,null,null,ia,null,enemy,null,null};

        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(ia);
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        machine.setExpectedGoals(goals);
        
        assertEquals(3, machine.smartMove(boardPositions));
    }
}
