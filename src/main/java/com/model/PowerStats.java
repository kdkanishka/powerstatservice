package com.model;

/**
 * Created by kanishka on 07/03/15.
 */
public class PowerStats {
    private float supplyVoltage;
    private float supplyAmp;
    private float supplyFrequency;
    private float totalKWattSeconds;

    public PowerStats(float supplyVoltage, float supplyAmp, float supplyFrequency, float totalKWattSeconds) {
        this.supplyVoltage = supplyVoltage;
        this.supplyAmp = supplyAmp;
        this.supplyFrequency = supplyFrequency;
        this.totalKWattSeconds = totalKWattSeconds;
    }

    public float getSupplyVoltage() {
        return supplyVoltage;
    }

    public void setSupplyVoltage(float supplyVoltage) {
        this.supplyVoltage = supplyVoltage;
    }

    public float getSupplyAmp() {
        return supplyAmp;
    }

    public void setSupplyAmp(float supplyAmp) {
        this.supplyAmp = supplyAmp;
    }

    public float getSupplyFrequency() {
        return supplyFrequency;
    }

    public void setSupplyFrequency(float supplyFrequency) {
        this.supplyFrequency = supplyFrequency;
    }

    public float getTotalKWattSeconds() {
        return totalKWattSeconds;
    }

    public void setTotalKWattSeconds(float totalKWattSeconds) {
        this.totalKWattSeconds = totalKWattSeconds;
    }
}
