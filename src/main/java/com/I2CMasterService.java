package com;

import com.i2c.I2CMaster;

/**
 * Created by kanishka on 07/03/15.
 */
public class I2CMasterService {
    public static void main(String[] args) {
        I2CMaster i2CMaster = new I2CMaster();
        //service begins
        while (true) {
            try {
                Thread.sleep(1000);
                String rowStatFromSlave = i2CMaster.askStatsFromSlave();
                System.out.println(rowStatFromSlave);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
