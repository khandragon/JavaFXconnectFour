package com.connectfour.data;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Seb
 */
public class BoardTest {
 /*   private Board board;

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
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);

        board.printBoard();
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
        board.addMove((byte)0, (byte)5);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)5);
        board.addMove((byte)0, (byte)5);

        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)4);

        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)5);
        board.addMove((byte)2, (byte)5);
        board.addMove((byte)2, (byte)4);

        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)4);

        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);

        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)5);

        board.addMove((byte)6, (byte)5);

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
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)5);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);

        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)4);
        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)4);
        board.addMove((byte)1, (byte)5);

        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)5);
        board.addMove((byte)2, (byte)5);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);

        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)4);

        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);

        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)4);

        board.addMove((byte)6, (byte)5);
        board.addMove((byte)6, (byte)5);
        board.addMove((byte)6, (byte)5);
        board.addMove((byte)6, (byte)4);
        board.addMove((byte)6, (byte)5);
        board.addMove((byte)6, (byte)5);

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
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)4);
        board.addMove((byte)0, (byte)5);
        board.addMove((byte)0, (byte)4);

        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)4);
        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)5);
        board.addMove((byte)1, (byte)4);

        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)5);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);

        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)5);

        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)5);

        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)5);

        board.addMove((byte)6, (byte)4);
        board.addMove((byte)6, (byte)4);
        board.addMove((byte)6, (byte)5);
        board.addMove((byte)6, (byte)4);
        board.addMove((byte)6, (byte)5);
        board.addMove((byte)6, (byte)5);

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
        board.addMove((byte)1, (byte)4);

        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);

        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)4);

        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)4);

        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);

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
        board.addMove((byte)1, (byte)4);

        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)5);
        board.addMove((byte)2, (byte)4);
        board.addMove((byte)2, (byte)4);

        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)4);
        board.addMove((byte)3, (byte)5);
        board.addMove((byte)3, (byte)5);

        board.addMove((byte)4, (byte)4);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)5);
        board.addMove((byte)4, (byte)4);

        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)4);
        board.addMove((byte)5, (byte)5);
        board.addMove((byte)5, (byte)4);

        board.addMove((byte)6, (byte)5);

        // Assert
        Assert.assertTrue(board.checkIfWin());
    }
    */
}
