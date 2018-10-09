package com.clarioninnovative.mvpdemo.api;

/**
 * Created by Ramesh on 09/10/18.
 */

public class Repos {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
