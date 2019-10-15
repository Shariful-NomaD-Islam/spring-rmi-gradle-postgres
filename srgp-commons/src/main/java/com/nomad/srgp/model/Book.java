package com.nomad.srgp.model;

import java.io.Serializable;

/**
 * @author Shariful Islam
 */
public class Book implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6539512726021043949L;
    private String id;
    private String name;
    private String authorName;

    public Book setId(String id) {
        this.id = id;
        return this;
    }

    public String getId() {
        return this.id;
    }

    public Book setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public Book setAuthorName(String authorName) {
        this.authorName = authorName;
        return this;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public String toString() {
        return "ID: '" + this.id + "', name: '" + this.name + "', author-name: '" + this.authorName + "'";
    }
}