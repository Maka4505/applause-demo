package com.mahlik.demo.dto;

import java.io.Serializable;

public class TesterWithExperience implements Serializable {

    private String fullName;
    private int experience;

    public TesterWithExperience(
            String testerFullName,
            int experience
    ) {
        this.fullName = testerFullName;
        this.experience = experience;
    }

    public String getFullName() {
        return fullName;
    }

    public TesterWithExperience setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public int getExperience() {
        return experience;
    }

    public TesterWithExperience setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    @Override
    public String toString() {
        return "TesterWithExperience{" +
                "testerFullName='" + fullName + '\'' +
                ", experience=" + experience +
                '}';
    }
}
