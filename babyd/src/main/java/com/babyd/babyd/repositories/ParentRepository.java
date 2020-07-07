package com.babyd.babyd.repositories;

import com.babyd.babyd.exceptions.EtAuthException;
import com.babyd.babyd.models.Parent;

import java.util.List;

public interface ParentRepository {
    int createParent(String first_name, String last_name, String email, String password) throws EtAuthException;

    Parent findByEmailAndPassword(String email, String password) throws EtAuthException;

    Integer getCountByEmail(String email);

    Parent findParentById(int id);

    List<Parent> getAllParents();

    void deleteAllParents();
}
