/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author alexmendez
 */
public class StrategyUtils {
    
    public static final char[] obtainMarkedPosition(Integer playerMoving, Integer[]boardPositions, String positions){
        
        for (int n = 0; n < 9; n++) {
            if (Objects.equals(playerMoving, boardPositions[n])) {
                positions += n;
            }
        }
        
        return positions.toCharArray();
    }
    
    
}
