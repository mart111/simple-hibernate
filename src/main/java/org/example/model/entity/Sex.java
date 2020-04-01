package org.example.model.entity;

public enum Sex {

    MALE("male"),
    FEMALE("female"),
    OTHER("other");
    private final String val;

    Sex(String val) {
        this.val = val;
    }

    public static Sex from(String value) {
        return Sex.valueOf(value);
    }

    public String getVal() {
        return val;
    }
}
