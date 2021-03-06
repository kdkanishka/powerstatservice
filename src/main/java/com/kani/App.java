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

        for (int i = 0; i < 100; i++) {


            byte dataToSlave = 10;
            device.write(dataToSlave);
            System.out.println("Data written to slave");

            Thread.sleep(1000);
//        byte dataFromSlave = (byte)device.read();
//        System.out.println("Received data from slave : " + dataFromSlave);
            final int BUFFER_SIZE = 32;
            byte[] dataFromSlave = new byte[BUFFER_SIZE];
            device.read(dataFromSlave, 0, BUFFER_SIZE);
            System.out.println("Received : " + Arrays.toString(dataFromSlave));

            byte[] filteredData = filterData(dataFromSlave);
            System.out.println("Filtered : " + Arrays.toString(filteredData));
            System.out.println("Value : " + new String(filteredData));
            System.out.println("\nDone!");

        }
    }

    public static byte[] filterData(byte[] dataFromSlave) {
        int firstMinusValIdx = -1;
        for (int i = 0; i < dataFromSlave.length; i++) {
            if(dataFromSlave[i] == -1){
                firstMinusValIdx = i;
                break;
            }
        }
        byte[] filteredData = new byte[firstMinusValIdx];
        if(firstMinusValIdx > -1){
            System.arraycopy(dataFromSlave,0,filteredData,0,firstMinusValIdx);
            return filteredData;
        }else{
            return dataFromSlave;
        }
    }

    public static int byteArrayToInt(byte[] array) {
        return ByteBuffer.wrap(array).getInt();
    }

    public static long byteArrayToLong(byte[] array) {
        return ByteBuffer.wrap(array).getLong();
    }
}
