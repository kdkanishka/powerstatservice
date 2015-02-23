package com.kani;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by kanishka on 23/02/15.
 */
public class Test {
    public static void main(String[] args) {
        byte[] floatBuff = int2ByteArray(1234567890);
        System.out.println(Arrays.toString(floatBuff));
        int deserializedFloat = byteArrayToInt(floatBuff);
        System.out.println("Deserialized int : " + deserializedFloat);
    }

    public static byte [] int2ByteArray (int value)
    {
        return ByteBuffer.allocate(4).putInt(value).array();
    }

    public static int byteArrayToInt(byte[] array){
        return ByteBuffer.wrap(array).getInt();
    }
}
