/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.bll;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author alexmendez
 */
public class ArtifitialGamer extends Gamer {

    public int smartMove(Integer[] boardPositions) {

        ArrayList<Integer> avPos = findEmptyPos(boardPositions);
        ArrayList<Integer> enemyPos = findTakenPos(boardPositions);
        evaluateTakenGoals(this.getExpectedGoals(), enemyPos);

        Integer bestMove = findFreqBestPos(avPos);

        if (null != bestMove) {
            boardPositions[bestMove] = (this.getOrderPosition());
            markPosition(bestMove);
        }
        
        return bestMove;

    }

    public Integer findFreqBestPos(ArrayList<Integer> avPositionsThisMove) {
        Map<Integer, Integer> posWinstadMap = new HashMap<>();

        System.out.println("positionsToevaluate:");
        for (int i = 0; i < avPositionsThisMove.size(); i++) {

            Integer positionToeval = avPositionsThisMove.get(i);

            System.out.println("positionl-" + positionToeval);

            //Contar en cuantos mov ganadores esta esta posicion
            int freq = 0;
//            String[] winnerMovs = this.getExpectedGoals();

            for (String avWinMovIA1 : this.getExpectedGoals()) {
                //Saber si en este movimiento ganador esta este posicion
//                if(avWinMovIA.contains(position.toString()))
//                    freq+=1;

                System.out.println("avWinMovIA1"+avWinMovIA1);

                if (avWinMovIA1.matches("(.*)" + positionToeval + "(.*)")) {
                    freq += 1;
                }

                if (null != this.getEarnedPositions() && this.getEarnedPositions().isEmpty()) {
                    for (Integer a : this.getEarnedPositions()) {
                        if (avWinMovIA1.matches("(.*)" + positionToeval + "(.*)")) {
//                            freq += (1 * this.getEarnedPositions().size());
                            freq += this.getEarnedPositions().size();
                        }
                    }
                }
            }

            System.out.println("MovGanadores:" + freq);

            posWinstadMap.put(positionToeval, freq);

        }
        Integer maxFreqPos = obtainMaxFreq(posWinstadMap);
        System.out.println("maxFrqPos:" + maxFreqPos);

        return maxFreqPos;
    }

    private ArrayList<Integer> findEmptyPos(Integer[] boardPositions) {
        ArrayList<Integer> posAv = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (null == boardPositions[i]) {
                posAv.add(i);
            }
        }
        return posAv;
    }

    private ArrayList<Integer> findTakenPos(Integer[] boardPositions) {
        ArrayList<Integer> posAv = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (null != boardPositions[i]) {
                if (this.getOrderPosition() != boardPositions[i]) {
                    posAv.add(i);
                }
            }
        }
        return posAv;
    }

    private Integer obtainMaxFreq(Map<Integer, Integer> posWinstadMap) {
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

    private void evaluateTakenGoals(ArrayList<String> expectedGoals, ArrayList<Integer> enemyPos) {
        ArrayList<String> goalToDel=new ArrayList<>();
        for (Integer posToDel : enemyPos) {

            for (int i=0; i<expectedGoals.size(); i++) {
//                String avWinMovIA1 : expectedGoals

                if (expectedGoals.get(i).matches("(.*)" + posToDel + "(.*)")) {
                    goalToDel.add(expectedGoals.get(i));
                    
                }
                
            }
            goalToDel.stream() .forEach(a->expectedGoals.remove(a));
            
            
        }
    }

}
