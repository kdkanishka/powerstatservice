package com.i2c;

/**
 * Created by kanishka on 07/03/15.
 */
public class DataParser {
    private DataParser() {

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
