/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connectfour.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Seb
 */
public class BoardTest {
    private Board board;
    
    @Before
    public void init(){
        board = new Board();
    }
    
    @Test
    public void checkValidVerticalWin(){
        // - - - - - - -
        // - - - - - - -
        // X - - - - - -
        // X - - - - - -
        // X - - - - - -
        // X - - - - - - 
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        Assert.assertTrue(board.checkIfWin());
    }
    
    @Test
    public void checkComplexValidVerticalWin(){
        // - - - - - - -
        // - - - - o - -
        // o - x - o - -
        // o - o - o - -
        // x x o x o o -
        // o o x x x o o 
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        Assert.assertTrue(board.checkIfWin());
    }
    
    @Test
    public void checkInvalidVerticalWin(){
        
    }
    
    @Test
    public void checkValidHorizontalWin(){
        
    }
    
    @Test
    public void checkInvalidHorizontalWin(){
        
    }
    
    @Test
    public void checkValidDiagonalWin(){
        
    }
    
    @Test
    public void checkinvalidDiagonalWin(){
        
    }
}
