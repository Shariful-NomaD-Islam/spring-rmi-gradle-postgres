package com.nomad.srgp.rmi;

import com.nomad.srgp.model.Person;

import java.util.List;

/**
 * @author Shariful Islam
 */
public interface PersonInterface {
    public boolean create(Person p);
    public Person getBasicInfo(String id);
    public String getName(String id);
    public List<Person> getAll();
}