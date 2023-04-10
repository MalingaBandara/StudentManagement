package com.developersstack.edumanage.repo.custom;

import com.developersstack.edumanage.entity.User;

import java.sql.SQLException;

public interface UserRepo {

    // save user
    public boolean saveUser (User user) throws SQLException, ClassNotFoundException;

    // load user
    public User loginUser (String email) throws SQLException, ClassNotFoundException;


}
