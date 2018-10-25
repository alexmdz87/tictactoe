/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author alexmendez
 */
public class StrategyUtils {
    
    public static final String[] MovWinner = {"012", "345", "678",
        "036", "147", "258",
        "048", "246"};
    
    public static final Integer[] CORNER_VALUES = {0,2,6,8};
    public static final Integer[] SIDES_VALUES = {1,3,5,7};
    public static final Integer CENTER_VALUE = 4;
    
    public static char[] obtainMarkedPosition(Integer playerMoving, Integer[]boardPositions, String positions){
        
        for (int n = 0; n < 9; n++) {
            if (Objects.equals(playerMoving, boardPositions[n])) {
                positions += n;
            }
        }
        
        return positions.toCharArray();
    }
    
    public static int findMovesByPosition(int positionToEval, ArrayList<String> goals, ArrayList<Integer> alreadyPos, boolean positiveMoves){
        int freq = 0;
        for (String avWinMovIA1 : goals) {

            if(positiveMoves){
                if (avWinMovIA1.matches("(.*)" + positionToEval + "(.*)")) {
                freq += 1;
                }
            }           

            if (null != goals && !goals.isEmpty()) {
                if(null!=alreadyPos){
                    for (Integer a : alreadyPos) {
                        if (avWinMovIA1.matches("(.*)" + a + "(.*)")) {
                            if (avWinMovIA1.matches("(.*)" + positionToEval + "(.*)")) {
                                            freq += 1;
                            }
                        }
                    }
                }
            }
        }

        return freq;
    }

    public static ArrayList<Integer> findEmptyPos(Integer[] boardPositions) {
        ArrayList<Integer> posAv = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (null == boardPositions[i]) {
                posAv.add(i);
            }
        }
        return posAv;
    }
    
    public static ArrayList<Integer> findEnemyPos(Integer[] boardPositions, int orderPosition) {
        ArrayList<Integer> posAv = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (null != boardPositions[i]) {
                if (orderPosition != boardPositions[i]) {
                    posAv.add(i);
                }
            }
        }
        return posAv;
    }

    public static Integer obtainMaxFreq(Map<Integer, Integer> posWinstadMap) {
        Integer keyMaxFreq = null;
        for (Integer key : posWinstadMap.keySet()) {
            if (null == keyMaxFreq) {
                keyMaxFreq = key;
            } else if (posWinstadMap.get(keyMaxFreq) < posWinstadMap.get(key)) {
                keyMaxFreq = key;
            }
        }
        return keyMaxFreq;
    }
    
    public static Integer validateBestProfits(Map<Integer, Integer> plusPosMap, Map<Integer, Integer> minusPosMap){
        Integer bestProfitKey=null;
        int bestProfit=0;
        for (Integer key : plusPosMap.keySet()) {
            int profit=0;
            profit+=plusPosMap.get(key);
            profit+=minusPosMap.get(key);
            
            if (bestProfit < profit){
                bestProfit = profit;
                bestProfitKey=key;
            }
            
        }
        return bestProfitKey;
    }

    public static ArrayList<String> filterTakenGoals(ArrayList<String> expectedGoals, ArrayList<Integer> enemyPos) {
        ArrayList<String> goalToDel=new ArrayList<>();
        enemyPos.stream().map((posToDel) -> {
            for (int i=0; i<expectedGoals.size(); i++) {
                if (expectedGoals.get(i).matches("(.*)" + posToDel + "(.*)")) {
                    goalToDel.add(expectedGoals.get(i));
                }
            }
            return posToDel;
        }).forEachOrdered((_item) -> {
            goalToDel.stream() .forEach(a->expectedGoals.remove(a));
        });
        return goalToDel;
    }

    public static Integer findPositivePrior(ArrayList<String> expectedGoals, ArrayList<Integer> myPositions) {
        Integer priorPositive=null;
        for (int i=0; i<expectedGoals.size(); i++) {
            
            char[] charMissing=expectedGoals.get(i).toCharArray();
            ArrayList<Integer> listGoalPost = new ArrayList<>(3);
            for(char golPos:charMissing){
                listGoalPost.add(golPos - '0');
            }

            myPositions.stream().filter((posToEval) -> (listGoalPost.contains(posToEval))).forEachOrdered((posToEval) -> {
                listGoalPost.remove(listGoalPost.indexOf(posToEval));
            });
            if(listGoalPost.size()==1)
                priorPositive=listGoalPost.get(0);
        }
        
        return priorPositive;
    }
    
    public static boolean evalTie(Integer[] boardPositions){
        return findEmptyPos(boardPositions).isEmpty();
    }
    
}
