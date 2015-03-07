package com.i2c;

import com.exception.DataParserException;
import com.model.PowerStats;

/**
 * Created by kanishka on 07/03/15.
 */
public class DataParser {
    private DataParser() {

    }

    public static void parsePowerStats(String rowVal, PowerStats powerStats) throws DataParserException {
        if (rowVal == null) {
            throw new DataParserException("Could not parse value :" + rowVal);
        }

        if (rowVal.endsWith("A")) {
            float suppAmp = Float.parseFloat(stripLastChar(rowVal));
            powerStats.setSupplyAmp(suppAmp);
        } else if (rowVal.endsWith("H")) {
            float suppFreq = Float.parseFloat(stripLastChar(rowVal));
            powerStats.setSupplyFrequency(suppFreq);
        } else if (rowVal.endsWith("W")) {
            float power = Float.parseFloat(stripLastChar(rowVal));
            powerStats.setTotalKWattSeconds(power);
        } else if (rowVal.endsWith("V")) {
            float volts = Float.parseFloat(stripLastChar(rowVal));
            powerStats.setSupplyVoltage(volts);
        } else {
            throw new DataParserException("Unknown suffix :" + stripLastChar(rowVal));
        }
    }

    private static String stripLastChar(String rowVal) {
        return rowVal.substring(0, rowVal.length() - 1);
    }

    public static byte[] filterData(byte[] dataFromSlave) {
        int firstMinusValIdx = -1;
        for (int i = 0; i < dataFromSlave.length; i++) {
            if (dataFromSlave[i] == -1) {
                firstMinusValIdx = i;
                break;
            }
        }
        byte[] filteredData = new byte[firstMinusValIdx];
        if (firstMinusValIdx > -1) {
            System.arraycopy(dataFromSlave, 0, filteredData, 0, firstMinusValIdx);
            return filteredData;
        } else {
            return dataFromSlave;
        }
    }
}
