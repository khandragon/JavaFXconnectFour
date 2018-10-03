package com.connectfour.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Seb
 */
public class BoardTest {
    private Board board;
    
    @Before
    public void init(){
        board = new Board('x','o');
    }
    
    @Test
    public void checkValidVerticalWin(){
        // - - - - - - -
        // - - - - - - -
        // x - - - - - -
        // x - - - - - -
        // x - - - - - -
        // x - - - - - - 
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        // Assert
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
        board.addMove(0, 'o');
        board.addMove(0, 'x');
        board.addMove(0, 'o');
        board.addMove(0, 'o');
        
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        
        board.addMove(2, 'x');
        board.addMove(2, 'o');
        board.addMove(2, 'o');
        board.addMove(2, 'x');
        
        board.addMove(3, 'x');
        board.addMove(3, 'x');
        
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        
        board.addMove(6, 'o');
        
        // Assert
        Assert.assertTrue(board.checkIfWin());
    }
    
    @Test
    public void checkNoWinFullBoard(){
        // x o x x o x o
        // x x x o o x o
        // o o x x x o x
        // x o o o x x o
        // x x o x o o o
        // x o x x o o o
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'o');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        board.addMove(1, 'o');
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        board.addMove(1, 'o');
        
        board.addMove(2, 'x');
        board.addMove(2, 'o');
        board.addMove(2, 'o');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        
        board.addMove(3, 'x');
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'x');
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        board.addMove(5, 'x');
        
        board.addMove(6, 'o');
        board.addMove(6, 'o');
        board.addMove(6, 'o');
        board.addMove(6, 'x');
        board.addMove(6, 'o');
        board.addMove(6, 'o');
        
        // Assert
        Assert.assertFalse(board.checkIfWin());
        Assert.assertTrue(board.isComplete());
    }
    
    @Test
    public void checkValidHorizontalWin(){
        // - - - o o o o
        // x x x o x x o
        // o o x x o x x
        // x o o o x o o
        // x x x o x o x
        // x o x x x o x
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'o');
        board.addMove(0, 'x');
        
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        board.addMove(1, 'o');
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'o');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'o');
        
        board.addMove(4, 'x');
        board.addMove(4, 'x');
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        
        board.addMove(6, 'x');
        board.addMove(6, 'x');
        board.addMove(6, 'o');
        board.addMove(6, 'x');
        board.addMove(6, 'o');
        board.addMove(6, 'o');
        
        // Assert
        Assert.assertTrue(board.checkIfWin());
        Assert.assertFalse(board.isComplete());
    }
    
    @Test
    public void checkValidAscendingDiagonalWin(){
        // - - - - - x -
        // - - - - x o -
        // - - - x o x -
        // - - x o o x - /
        // - - x x o o - /
        // - x x o x x - /
        // / / / /
        board.addMove(1, 'x');
        
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'x');
        
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        
        // Assert
        Assert.assertTrue(board.checkIfWin());
    }
    
    @Test
    public void checkValidDescendingDiagonalWin(){
        // - - x - - x - /
        // - - x - x o - /
        // - - o o o x - /
        // - - x o o x -
        // - - x x o o -
        // - x x o x x o
        // / / / /    
        board.addMove(1, 'x');
        
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'o');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'o');
        
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'o');
        board.addMove(4, 'x');
        
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        
        board.addMove(6, 'o');
        
        // Assert
        Assert.assertTrue(board.checkIfWin());
    }
    
    @Test
    public void checkFullBoardComputerMove()
    {
        // x o o o o o o
        // x x x o x x o
        // o o x x o x x
        // x o o o x o o
        // x x x o x o x
        // x o x x x o x
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'o');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        board.addMove(1, 'o');
        board.addMove(1, 'o');
        board.addMove(1, 'x');
        board.addMove(1, 'o');
        
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'o');
        board.addMove(2, 'x');
        board.addMove(2, 'x');
        board.addMove(2, 'o');
        
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'o');
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        board.addMove(3, 'o');
        
        board.addMove(4, 'x');
        board.addMove(4, 'x');
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        board.addMove(4, 'x');
        board.addMove(4, 'o');
        
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        board.addMove(5, 'x');
        board.addMove(5, 'o');
        
        board.addMove(6, 'x');
        board.addMove(6, 'x');
        board.addMove(6, 'o');
        board.addMove(6, 'x');
        board.addMove(6, 'o');
        board.addMove(6, 'o');
        
        int number = board.computerMove();
        
        Assert.assertEquals(-1, number);
    }
    
    @Test
    public void pickWin()
    {
        // - - - - - - -
        // - - - - - - -
        // - - - - - - -
        // x - - - - - -
        // x - - - - - -
        // x o o o - - -
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        board.addMove(1, 'o');
        board.addMove(2, 'o');
        board.addMove(3, 'o');
        
        int number = board.computerMove();
        
        Assert.assertEquals(4, number);
    }
    
    @Test
    public void pickBlock()
    {
        // - - - - - - -
        // - - - - - - -
        // - - - - - - -
        // x - - - - - x
        // x - - o - x o
        // x - - x - o o
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        board.addMove(0, 'x');
        
        board.addMove(3, 'x');
        board.addMove(3, 'o');
        
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        
        board.addMove(5, 'o');
        board.addMove(5, 'o');
        board.addMove(5, 'x');
        
        int number = board.computerMove();
        
        Assert.assertEquals(0, number);
    }
    
    @Test
    public void pickBestChoice()
    {
        // - - - - - - -
        // - - - - - - -
        // - - - - - - -
        // - - - - - - -
        // - - - - - - -
        // x x - o - x -
        board.addMove(0, 'x');
        board.addMove(1, 'x');
        board.addMove(3, 'o');
        board.addMove(5, 'x');
        
        int number = board.computerMove();
        
        Assert.assertEquals(2, number);
    }
}
