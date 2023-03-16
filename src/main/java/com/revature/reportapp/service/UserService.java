package com.revature.reportapp.service;

import com.revature.reportapp.entity.Login;
import com.revature.reportapp.entity.User;

import java.util.List;

public interface UserService {
    User insert(User user);
    User update(User user);
    User getById(Long user_id);
    List<User> getAll();
    List<User>getByUserName(String userName);
    User getByNameAndPassword(Login login);
    boolean delete (Long user_id);


}
