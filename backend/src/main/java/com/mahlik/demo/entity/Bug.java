package com.mahlik.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Bug {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bug_id_seq"
    )
    @SequenceGenerator(
            allocationSize = 1,
            name = "bug_id_seq",
            sequenceName = "bug_id_seq"
    )
    @JsonProperty("bugId")
    private long id;

    private long deviceId;
    private long testerId;

    public Bug() {
    }

    public long getId() {
        return id;
    }

    public Bug setId(long id) {
        this.id = id;
        return this;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public Bug setDeviceId(long deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public long getTesterId() {
        return testerId;
    }

    public Bug setTesterId(long testerId) {
        this.testerId = testerId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return id == bug.id && deviceId == bug.deviceId && testerId == bug.testerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, deviceId, testerId);
    }

    @Override
    public String toString() {
        return "Bug{" +
                "bugId=" + id +
                ", deviceId=" + deviceId +
                ", testerId=" + testerId +
                '}';
    }
}
