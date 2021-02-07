package com.mahlik.demo.entity;

import java.util.Objects;


public class TesterToDevice {

    private long deviceId;
    private long testerId;

    public TesterToDevice() {
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesterToDevice that = (TesterToDevice) o;
        return deviceId == that.deviceId && testerId == that.testerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deviceId, testerId);
    }

    @Override
    public String toString() {
        return "TesterToDevice{" +
                "deviceId=" + deviceId +
                ", testerId=" + testerId +
                '}';
    }
}
