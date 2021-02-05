package com.mahlik.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class Device {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "device_id_seq"
    )
    @SequenceGenerator(
            allocationSize = 1,
            name = "device_id_seq",
            sequenceName = "device_id_seq"
    )
    @JsonProperty("deviceId")
    private long id;

    private String description;

    public Device() {
    }

    public long getId() {
        return id;
    }

    public Device setId(long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Device setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public String toString() {
        return "Device{" +
                "deviceId=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
