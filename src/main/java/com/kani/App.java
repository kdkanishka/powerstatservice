package com.kani;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by kanishka on 23/02/15.
 */
public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int SLAVE_ADDR = 0x04;
        System.out.println("Trying to read from I2C slave");

        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        System.out.println("Connected to I2C bus");
        I2CDevice device = bus.getDevice(SLAVE_ADDR);

        byte dataToSlave = 10;
//        device.write(dataToSlave);
//        System.out.println("Data written to slave");

        Thread.sleep(1000);
//        byte dataFromSlave = (byte)device.read();
//        System.out.println("Received data from slave : " + dataFromSlave);
        byte[] dataFromSlave = new byte[4];
        device.read(dataFromSlave,0,4);

        System.out.println("Received : " + Arrays.toString(dataFromSlave));
        System.out.println("Value : " + byteArrayToInt(dataFromSlave));
        System.out.println("\nDone!");
    }

    public static int byteArrayToInt(byte[] array){
        return ByteBuffer.wrap(array).getInt();
    }
}
