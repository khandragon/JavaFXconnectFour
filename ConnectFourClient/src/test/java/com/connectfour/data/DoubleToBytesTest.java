/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.connectfour.data;

import java.nio.ByteBuffer;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author antho
 */
public class DoubleToBytesTest {
    DoubleToBytes dtb;
    
    @Before
    public void init()
    {
        dtb = new DoubleToBytes();
    }
    
    @Test
    public void positiveWholeBytesTest()
    {
        System.out.println("Testing positive whole number to bytes.");
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(21);
        System.out.println("Original:" + arrayToString(old));
        byte[] obtained = dtb.doubleToByteArray(21);
        System.out.println("New:" + arrayToString(obtained));
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void positiveDecimalBytesTest()
    {
        System.out.println("Testing positive decimal number to bytes.");
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(81.45);
        System.out.println("Original:" + arrayToString(old));
        byte[] obtained = dtb.doubleToByteArray(81.45);
        System.out.println("New:" + arrayToString(obtained));
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void negativeWholeBytesTest()
    {
        System.out.println("Testing negative whole number to bytes.");
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(-14);
        System.out.println("Original:" + arrayToString(old));
        byte[] obtained = dtb.doubleToByteArray(-14);
        System.out.println("New:" + arrayToString(obtained));
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void negativeDecimalBytesTest()
    {
        System.out.println("Testing negative decimal number to bytes.");
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(-45.56);
        System.out.println("Original:" + arrayToString(old));
        byte[] obtained = dtb.doubleToByteArray(-45.56);
        System.out.println("New:" + arrayToString(obtained));
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void positiveWholeNumberTest()
    {
        System.out.println("Testing positive whole bytes to number.");
        byte[] bites = new byte[8];
        double old = 78;
        System.out.println("Original:" + old);
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        System.out.println("New:" + obtained);
        assertEquals(old, obtained, 0);
    }
    
    @Test
    public void positiveDecimalNumberTest()
    {
        System.out.println("Testing positive decimal bytes to number.");
        byte[] bites = new byte[8];
        double old = 13.76;
        System.out.println("Original:" + old);
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        System.out.println("New:" + obtained);
        assertEquals(old, obtained, 2);
    }
    
    @Test
    public void negativeWholeNumberTest()
    {
        System.out.println("Testing negative whole bytes to number.");
        byte[] bites = new byte[8];
        double old = -90;
        System.out.println("Original:" + old);
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        System.out.println("New:" + obtained);
        assertEquals(old, obtained, 2);
    }
    
    @Test
    public void negativeDecimalNumberTest()
    {
        System.out.println("Testing negative decimal bytes to number.");
        byte[] bites = new byte[8];
        double old = -35.16;
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        assertEquals(old, obtained, 2);
    }
    
    @Test
    public void zeroNumberTest()
    {
        System.out.println("Testing zero number to bytes.");
        byte[] bites = new byte[8];
        double old = 0;
        System.out.println("Original:" + old);
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        System.out.println("New:" + obtained);
        assertEquals(old, obtained, 0);
    }
    
    @Test
    public void zeroBytesTest()
    {
        System.out.println("Testing zero bytes to number.");
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(0);
        System.out.println("Original:" + arrayToString(old));
        byte[] obtained = dtb.doubleToByteArray(0);
        System.out.println("New:" + arrayToString(obtained));
        Assert.assertArrayEquals(old,obtained);
    }
    
    private String arrayToString(byte[] bites)
    {
        String str = "";
        for (int i = 0; i < bites.length; i++)
        {
            str += bites[i];
        }
        return str;
    }
}
