package com.connectfour.data;

public class PacketInfo {


    //specifies the game mode
    public static final byte MOVE = 1;
    public static final byte QUIT = 2;
    public static final byte PLAY = 3;

    //specifies which player just moved
    public static final byte PLAYER_ONE = 4;
    public static final byte PLAYER_TWO = 5;

    //used as place holder
    public static final byte SPACE = 99;
}
