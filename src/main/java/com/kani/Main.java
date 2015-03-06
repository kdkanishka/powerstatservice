package com.kani;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by kanishka on 07/03/15.
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        final int SLAVE_ADDR = 0x04;
        System.out.println("Trying to read from I2C slave");

        while(true) {
            Thread.sleep(1000);
            I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
            System.out.println("Connected to I2C bus");
            I2CDevice device = bus.getDevice(SLAVE_ADDR);

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
}
