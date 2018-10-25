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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author alexmendez
 */
public class JUnitSmartSecondOpenMov {

    public JUnitSmartSecondOpenMov() {
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
        int enemy=2;
        int ia=1;
        //Positions already taken
        Integer[] boardPositions={enemy,null,null,null,ia,null,null,null,null};

        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(ia);
        
        ArrayList<Integer> availablePos = StrategyUtils.findEmptyPos(boardPositions);
        ArrayList<Integer> enemyPos = StrategyUtils.findEnemyPos(boardPositions,ia);
        ArrayList<Integer> myPositions = StrategyUtils.findEnemyPos(boardPositions,enemy);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        ArrayList<String> goalForEnemy=StrategyUtils.filterTakenGoals(goals, enemyPos);
        StrategyUtils.filterTakenGoals(goalForEnemy, myPositions);
        
        int[] bestPositiveMoveByPosition={2,3,2,3,3,3,2};
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
        int enemy=2;
        int ia=1;
        //Positions already taken
        Integer[] boardPositions={enemy,null,null,null,ia,null,null,null,null};

        ArtifitialGamer machine = new ArtifitialGamer();
        machine.setOrderPosition(ia);
        
        ArrayList<Integer> availablePos = StrategyUtils.findEmptyPos(boardPositions);
        ArrayList<Integer> enemyPos = StrategyUtils.findEnemyPos(boardPositions,ia);
        ArrayList<Integer> myPositions = StrategyUtils.findEnemyPos(boardPositions,enemy);
        
        ArrayList<String> goals=new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        ArrayList<String> goalForEnemy=StrategyUtils.filterTakenGoals(goals, enemyPos);
        StrategyUtils.filterTakenGoals(goalForEnemy, myPositions);
        
        int[] bestBlockingMoveByPosition={1,1,1,0,1,0,0};
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
    public void evaluateBestPositionByProfits() {
        int enemy=2;
        int ia=1;
        //Positions already taken
        Integer[] boardPositions={enemy,null,null,null,ia,null,null,null,null};
        
        //find base positive movements
        int[] bestPositiveMoveByPosition={2,3,2,3,3,3,2};
        
        Map<Integer, Integer> plusPosMap = new HashMap<>();
                plusPosMap.put(1, bestPositiveMoveByPosition[0]);
                plusPosMap.put(2, bestPositiveMoveByPosition[1]);
                plusPosMap.put(3, bestPositiveMoveByPosition[2]);
                plusPosMap.put(5, bestPositiveMoveByPosition[3]);
                plusPosMap.put(6, bestPositiveMoveByPosition[4]);
                plusPosMap.put(7, bestPositiveMoveByPosition[5]);
                plusPosMap.put(8, bestPositiveMoveByPosition[6]);
        
        //find based negative movements
        int[] bestBlockingMoveByPosition={1,1,1,0,1,0,0};
        Map<Integer, Integer> minusPosMap = new HashMap<>();
                minusPosMap.put(1, bestBlockingMoveByPosition[0]);
                minusPosMap.put(2, bestBlockingMoveByPosition[1]);
                minusPosMap.put(3, bestBlockingMoveByPosition[2]);
                minusPosMap.put(5, bestBlockingMoveByPosition[3]);
                minusPosMap.put(6, bestBlockingMoveByPosition[4]);
                minusPosMap.put(7, bestBlockingMoveByPosition[5]);
                minusPosMap.put(8, bestBlockingMoveByPosition[6]);
        
        Integer expectedBestMove=2;
        Integer bestProfitMove=StrategyUtils.validateBestProfits(plusPosMap,minusPosMap);
     
        Assert.assertEquals(expectedBestMove, bestProfitMove);
    }

}
