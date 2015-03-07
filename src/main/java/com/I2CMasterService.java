package com;

import com.exception.DataParserException;
import com.i2c.DataParser;
import com.i2c.I2CMaster;
import com.model.PowerStats;

/**
 * Created by kanishka on 07/03/15.
 */
public class I2CMasterService {
    public static void main(String[] args) {
        I2CMaster i2CMaster = new I2CMaster();
        PowerStats powerStats = new PowerStats();

        //service begins
        while (true) {
            try {
                Thread.sleep(1000);
                String rowStatFromSlave = i2CMaster.askStatsFromSlave();
                System.out.println(rowStatFromSlave);
                DataParser.parsePowerStats(rowStatFromSlave, powerStats);
                System.out.println(powerStats);

            } catch (DataParserException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
