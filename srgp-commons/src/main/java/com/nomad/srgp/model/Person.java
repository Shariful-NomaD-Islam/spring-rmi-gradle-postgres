package com.nomad.srgp.model;

import java.io.Serializable;

/**
 * @author Shariful Islam
 */
public class Person implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2221229933095843250L;
    private String id;
    private String name;
    private String address;

    public Person setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Person setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getAddress() {
        return this.address;
    }


    public String toString() {
        return "ID: '" + this.id + "', name: '" + this.name + "', address: '" + this.address + "'";
    }
}