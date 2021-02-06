package com.mahlik.demo.dto;

import java.io.Serializable;
import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TesterWithExperience that = (TesterWithExperience) o;
        return experience == that.experience && fullName.equals(that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, experience);
    }

    @Override
    public String toString() {
        return "TesterWithExperience{" +
                "testerFullName='" + fullName + '\'' +
                ", experience=" + experience +
                '}';
    }
}
