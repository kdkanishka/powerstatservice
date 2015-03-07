package com.i2c;

import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * Created by kanishka on 07/03/15.
 */
public class ZensorIntegration {

    public String getInstruction() {
        String fileName = "/home/pi/zensor/itg";
        String result = null;
        InputStream in = null;
        try {
            in = new FileInputStream(fileName);
            result = IOUtils.toString(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null)
                IOUtils.closeQuietly(in);
        }

        OutputStream out = null;
        try{
            out = new FileOutputStream(fileName);
            IOUtils.write("",out);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(out != null)
                IOUtils.closeQuietly(out);
        }
        return result;
    }
}
