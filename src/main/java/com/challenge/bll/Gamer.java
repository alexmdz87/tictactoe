/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.bll;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author alexmendez
 */
public class Gamer {
    
    private int orderPosition;
    private ArrayList<Integer> earnedPositions;
    private Integer[][] expectedPosilibities;
    private ArrayList<String> expectedGoals;
    
    public Gamer(){
        earnedPositions = new ArrayList<>();
        expectedGoals = new ArrayList<>();
    }
    
    public void markPosition(int position){
        earnedPositions.add(position);
        Collections.sort(earnedPositions);
    }
    
    //Getters + Setters

    public int getOrderPosition() {
        return orderPosition;
    }

    public void setOrderPosition(int orderPosition) {
        this.orderPosition = orderPosition;
    }

    public ArrayList<Integer> getEarnedPositions() {
        return earnedPositions;
    }

    public void setEarnedPositions(ArrayList<Integer> earnedPositions) {
        this.earnedPositions = earnedPositions;
    }

    public Integer[][] getExpectedPosilibities() {
        return expectedPosilibities;
    }

    public void setExpectedPosilibities(Integer[][] expectedPosilibities) {
        this.expectedPosilibities = expectedPosilibities;
    }

    public ArrayList<String> getExpectedGoals() {
        return expectedGoals;
    }

    public void setExpectedGoals(ArrayList<String> expectedGoals) {
        this.expectedGoals = expectedGoals;
    }
    
}
