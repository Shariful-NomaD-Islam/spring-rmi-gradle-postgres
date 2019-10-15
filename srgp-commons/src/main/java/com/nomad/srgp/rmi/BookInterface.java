package com.nomad.srgp.rmi;

import com.nomad.srgp.model.Book;

/**
 * @author Shariful Islam
 */
public interface BookInterface {
    public Book getFullData(String id);
}