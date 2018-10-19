package test.java.com.connectfour.data;

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
        board = new Board();
    }
    
    @Test
    public void checkValidVerticalWin(){
        // - - - - - - -
        // - - - - - - -
        // x - - - - - -
        // x - - - - - -
        // x - - - - - -
        // x - - - - - - 
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        
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
        board.addMove(0, PacketInfo.PLAYER_TWO);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_TWO);
        board.addMove(0, PacketInfo.PLAYER_TWO);
        
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        
        board.addMove(6, PacketInfo.PLAYER_TWO);
        
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
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_TWO);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        
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
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_TWO);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        
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
        board.addMove(1, PacketInfo.PLAYER_ONE);
        
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        
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
        board.addMove(1, PacketInfo.PLAYER_ONE);
        
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        
        board.addMove(6, PacketInfo.PLAYER_TWO);
        
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
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_TWO);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        
        
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        board.addMove(1, PacketInfo.PLAYER_TWO);
        
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_ONE);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        board.addMove(4, PacketInfo.PLAYER_ONE);
        board.addMove(4, PacketInfo.PLAYER_TWO);
        
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_ONE);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        board.addMove(6, PacketInfo.PLAYER_TWO);
        
        byte number = board.computerMove();
        
        Assert.assertEquals((byte)-1, number);
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
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        
        board.addMove(1, PacketInfo.PLAYER_TWO);
        board.addMove(2, PacketInfo.PLAYER_TWO);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        
        byte number = board.computerMove();
        
        Assert.assertEquals((byte)4, number);
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
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(0, PacketInfo.PLAYER_ONE);
        
        board.addMove(3, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        
        byte number = board.computerMove();
        
        Assert.assertEquals((byte)0, number);
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
        board.addMove(0, PacketInfo.PLAYER_ONE);
        board.addMove(1, PacketInfo.PLAYER_ONE);
        board.addMove(3, PacketInfo.PLAYER_TWO);
        board.addMove(5, PacketInfo.PLAYER_ONE);
        
        byte number = board.computerMove();
        
        Assert.assertEquals((byte)2, number);
    }
}