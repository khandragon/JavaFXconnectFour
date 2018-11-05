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
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(21);
        byte[] obtained = dtb.doubleToByteArray(21);
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void positiveDecimalBytesTest()
    {
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(81.45);
        byte[] obtained = dtb.doubleToByteArray(81.45);
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void negativeWholeBytesTest()
    {
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(-14);
        byte[] obtained = dtb.doubleToByteArray(-14);
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void negativeDecimalBytesTest()
    {
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(-45.56);
        byte[] obtained = dtb.doubleToByteArray(-45.56);
        Assert.assertArrayEquals(old,obtained);
    }
    
    @Test
    public void positiveWholeNumberTest()
    {
        byte[] bites = new byte[8];
        double old = 78;
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        assertEquals(old, obtained, 0);
    }
    
    @Test
    public void positiveDecimalNumberTest()
    {
        byte[] bites = new byte[8];
        double old = 13.76;
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        assertEquals(old, obtained, 2);
    }
    
    @Test
    public void negativeWholeNumberTest()
    {
        byte[] bites = new byte[8];
        double old = -90;
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        assertEquals(old, obtained, 2);
    }
    
    @Test
    public void negativeDecimalNumberTest()
    {
        byte[] bites = new byte[8];
        double old = -35.16;
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        assertEquals(old, obtained, 2);
    }
    
    @Test
    public void zeroNumberTest()
    {
        byte[] bites = new byte[8];
        double old = 0;
        ByteBuffer.wrap(bites).putDouble(old);
        double obtained = dtb.byteArrayToDouble(bites);
        assertEquals(old, obtained, 0);
    }
    
    @Test
    public void zeroBytesTest()
    {
        byte[] old = new byte[8];
        ByteBuffer.wrap(old).putDouble(0);
        byte[] obtained = dtb.doubleToByteArray(0);
        Assert.assertArrayEquals(old,obtained);
    }
}
