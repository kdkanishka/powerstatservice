package com.i2c;

import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by kanishka on 07/03/15.
 */
public class I2CMaster {
    final int SLAVE_ADDR = 0x04;

    public String askStatsFromSlave(String senzorCommand) throws IOException {
        I2CBus bus = I2CFactory.getInstance(I2CBus.BUS_1);
        System.out.println("Connected to I2C bus");
        I2CDevice device = bus.getDevice(SLAVE_ADDR);

        byte dataToSlave = DataParser.parseZensorCommand(senzorCommand.trim());
        System.out.println("DATA TO SL " + dataToSlave);
        if (dataToSlave > 0) {
            System.out.println("Command to slave :" + dataToSlave);
            device.write(dataToSlave);
            System.out.println("Data written to slave");
        }

        final int BUFFER_SIZE = 32;
        byte[] dataFromSlave = new byte[BUFFER_SIZE];
        device.read(dataFromSlave, 0, BUFFER_SIZE);
        byte[] filteredData = DataParser.filterData(dataFromSlave);
        System.out.println("Received : " + Arrays.toString(dataFromSlave));
        String rowValue = new String(filteredData);
        return rowValue;
    }
}
