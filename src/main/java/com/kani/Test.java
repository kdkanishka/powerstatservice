package com.kani;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by kanishka on 23/02/15.
 */
public class Test {
    public static void main(String[] args) {
        byte[] floatBuff = long2ByteArray(1234567890);
        System.out.println(Arrays.toString(floatBuff));
        int deserializedFloat = byteArrayToLong(floatBuff);
        System.out.println("Deserialized int : " + deserializedFloat);

//        String str = "HEAD / HTTP/1.0\r\n\r\n";
//        System.out.println(str.length());


        int i=123456;
        System.out.println((byte )((i >> 24) & 0xff));
        System.out.println((byte )((i >> 16) & 0xff));
        System.out.println((byte )((i >> 8) & 0xff));
        System.out.println((byte )(i & 0xff));

    }

    public static byte [] long2ByteArray (int value)
    {
        return ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
    }

    public static int byteArrayToLong(byte[] array){
        return ByteBuffer.wrap(array).getInt();
    }
}
