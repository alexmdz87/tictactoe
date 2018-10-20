/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.challenge.view;

import com.challenge.bll.Gamer;
import com.challenge.bll.ArtifitialGamer;
import com.utils.StrategyUtils;
import java.util.Arrays;
import java.util.Collections;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author alexmendez
 */
@ManagedBean(name = "game")
@ViewScoped
public class GameBean {

    private Integer[] boardPositions;
    private String state;
    private Gamer human;
    private ArtifitialGamer machine;

    private Integer winner;

    private static final String[] MovWinner = {"012", "345", "678",
        "036", "147", "258",
        "048", "246"};

    public GameBean() {
        boardPositions = new Integer[9];
        state = "";
        human = new Gamer();
        machine = new ArtifitialGamer();

    }

    public void startGame() {
        state = "started";
        Collections.addAll(machine.getExpectedGoals(), MovWinner);

        //Assign order player position of the machine
        machine.setOrderPosition((1 == human.getOrderPosition()) ? 2 : 1);

        //If machine is player One needs to move first
        if (2 == human.getOrderPosition()) {
            machine.smartMove(boardPositions);
        }

        //machine.setExpectedGoals(MovWinner);
    }

    public void humanMoves(Integer position) {
        boardPositions[position - 1] = human.getOrderPosition();
        human.markPosition(position);
        validatePosibleWinner(human.getOrderPosition());

        if ("started".equals(state)) {
            machine.smartMove(boardPositions);
            validatePosibleWinner(machine.getOrderPosition());
        }
    }

    private boolean validatePosibleWinner(Integer playerMoving) {
        
        char[] charCurrentPos = StrategyUtils.obtainMarkedPosition(playerMoving, boardPositions, "");

        if (2<charCurrentPos.length) {
            
            Arrays.sort(charCurrentPos);
            
            for (String movToWin :MovWinner) {             
                char[] charWinnerPos = movToWin.toCharArray();

                if(charCurrentPos.length>3)               
                
                if(Arrays.binarySearch(charCurrentPos, charWinnerPos[0])>=0)
                    if(Arrays.binarySearch(charCurrentPos, charWinnerPos[1])>=0)
                        if(Arrays.binarySearch(charCurrentPos, charWinnerPos[2])>=0){
                            winner = playerMoving;
                            state = "finished";
                            return true;
                        }
            }
        }
        return false;
    }

    //Getters + Setters
    public Integer[] getBoardPositions() {
        return boardPositions;
    }

    public void setBoardPositions(Integer[] boardPositions) {
        this.boardPositions = boardPositions;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getWinner() {
        return winner;
    }

    public void setWinner(Integer winner) {
        this.winner = winner;
    }

    public Gamer getHuman() {
        return human;
    }

    public void setHuman(Gamer human) {
        this.human = human;
    }

    public ArtifitialGamer getMachine() {
        return machine;
    }

    public void setMachine(ArtifitialGamer machine) {
        this.machine = machine;
    }

}
