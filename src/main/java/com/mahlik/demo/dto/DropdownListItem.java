package com.mahlik.demo.dto;

import java.util.Objects;

public class DropdownListItem {
    private Long id;
    private String label;

    public DropdownListItem(Long id, String label) {
        this.id = id;
        this.label = label;
    }

    public Long getId() {
        return id;
    }

    public DropdownListItem setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public DropdownListItem setLabel(String label) {
        this.label = label;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DropdownListItem that = (DropdownListItem) o;
        return Objects.equals(id, that.id) && Objects.equals(label, that.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label);
    }

    @Override
    public String toString() {
        return "DropdownListItem{" +
                "id=" + id +
                ", label='" + label + '\'' +
                '}';
    }

}
