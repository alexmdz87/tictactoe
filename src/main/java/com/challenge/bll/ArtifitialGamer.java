/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.bll;

import com.utils.StrategyUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alexmendez
 */
public class ArtifitialGamer extends Gamer {
    
    public int smartMove(Integer[] boardPositions){
        ArrayList<Integer> availablePos = StrategyUtils.findEmptyPos(boardPositions);
        ArrayList<Integer> enemyPos = StrategyUtils.findEnemyPos(boardPositions,this.getOrderPosition());
        ArrayList<Integer> myPositions = StrategyUtils.findEnemyPos(boardPositions,this.getOrderPosition()==1?2:1);
        
        ArrayList<String> goalForEnemy=StrategyUtils.filterTakenGoals(this.getExpectedGoals(), enemyPos);
        StrategyUtils.filterTakenGoals(goalForEnemy, myPositions);

        ArrayList<String> goals= new ArrayList<>();
        Collections.addAll(goals, StrategyUtils.MovWinner);
        StrategyUtils.filterTakenGoals(goals, myPositions);
        
        //find positive priority
        Integer priorPosition = StrategyUtils.findPositivePrior(this.getExpectedGoals(), myPositions);
        if(null==priorPosition)
            priorPosition = StrategyUtils.findPositivePrior(goals, enemyPos);
        
        if(null!=priorPosition){
            boardPositions[priorPosition] = (this.getOrderPosition());
            markPosition(priorPosition);
            return priorPosition;
        }
    
        //find based positive movements
        Map<Integer, Integer> plusPosMap = mapPointsByPosition(availablePos, myPositions, true, this.getExpectedGoals());
        //find based negative movements
        Map<Integer, Integer> minusPosMap = mapPointsByPosition(availablePos, enemyPos, false, goalForEnemy);
        
        Integer bestProfitMove=StrategyUtils.validateBestProfits(plusPosMap,minusPosMap);
        
        //make a movement because no body is going to win
        if(null==bestProfitMove){
            bestProfitMove = availablePos.get(0);
        }
        boardPositions[bestProfitMove] = (this.getOrderPosition());
        markPosition(bestProfitMove);
        
        return bestProfitMove;

    }

    public Integer findFreqBestPos(ArrayList<Integer> availablePositionsThisMove, ArrayList<Integer> gamerPositions, boolean positiveMoves, ArrayList<String> involvedGoal) {        
        Map<Integer, Integer> posMap = mapPointsByPosition(availablePositionsThisMove, gamerPositions, positiveMoves, involvedGoal);
        Integer maxFreqPos = StrategyUtils.obtainMaxFreq(posMap);

        return maxFreqPos;
    }
    
    public Map<Integer, Integer> mapPointsByPosition(ArrayList<Integer> emptyPositionsThisMove, ArrayList<Integer> gamerPositions, boolean positiveMoves, ArrayList<String> involvedGoals){
        Map<Integer, Integer> posWinstadMap = new HashMap<>();

        for (int i = 0; i < emptyPositionsThisMove.size(); i++) {
            Integer positionToeval = emptyPositionsThisMove.get(i);

            //Contar en cuantos mov ganadores esta esta posicion
            int freq = StrategyUtils.findMovesByPosition(positionToeval,involvedGoals,gamerPositions, positiveMoves);

            posWinstadMap.put(positionToeval, freq);
        }
        return posWinstadMap;
    }

}
