package com.mahlik.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tester {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "tester_id_seq"
    )
    @SequenceGenerator(
            name = "tester_id_seq",
            sequenceName = "tester_id_seq"
    )
    @JsonProperty("testerId")
    private long id;

    private String firstName;
    private String lastName;
    private String country;
    private Date lastLogin;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Set<Device> devices = new HashSet<>();

    public Tester() {
    }

    public long getId() {
        return id;
    }

    public Tester setId(long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Tester setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Tester setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Tester setCountry(String country) {
        this.country = country;
        return this;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public Tester setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }

    public Set<Device> getDevices() {
        return devices;
    }

    public Tester setDevices(Set<Device> devices) {
        this.devices = devices;
        return this;
    }

    @Override
    public String toString() {
        return "Tester{" +
                "testerId=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", country='" + country + '\'' +
                ", lastLogin=" + lastLogin +
                ", devices=" + devices +
                '}';
    }
}
