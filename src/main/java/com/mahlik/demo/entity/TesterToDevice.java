package com.mahlik.demo.entity;

import javax.persistence.*;


public class TesterToDevice {

    private long deviceId;
    private long testerId;

    public TesterToDevice() {}

    public TesterToDevice(
            long deviceId,
            long testerId
    ) {
        this.deviceId = deviceId;
        this.testerId = testerId;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public TesterToDevice setDeviceId(long deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public long getTesterId() {
        return testerId;
    }

    public TesterToDevice setTesterId(long testerId) {
        this.testerId = testerId;
        return this;
    }

    @Override
    public String toString() {
        return "TesterToDevice{" +
                "deviceId=" + deviceId +
                ", testerId=" + testerId +
                '}';
    }
}
