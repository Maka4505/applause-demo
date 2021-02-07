package com.mahlik.demo.dto;

import java.io.Serializable;
import java.util.Objects;

public class TesterWithExperience implements Serializable {

    private long id;
    private String fullName;
    private int experience;

    public TesterWithExperience(
            long id,
            String testerFullName,
            int experience
    ) {
        this.id = id;
        this.fullName = testerFullName;
        this.experience = experience;
    }

    public long getId() {
        return id;
    }

    public TesterWithExperience setId(long id) {
        this.id = id;
        return this;
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
        return id == that.id && experience == that.experience && Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullName, experience);
    }

    @Override
    public String toString() {
        return "TesterWithExperience{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", experience=" + experience +
                '}';
    }
}
