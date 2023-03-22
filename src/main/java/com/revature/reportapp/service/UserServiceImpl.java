package com.revature.reportapp.service;

import com.revature.reportapp.dto.Login;
import com.revature.reportapp.entity.User;
import com.revature.reportapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService{
    @Autowired
    UserRepo userRepo;

    @Override
    public User insert(User user) {
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @Override
    public List<User> getByUserName(String userName) {
        return userRepo.getByUserName(userName);
    }

    @Override
    public User getByUserNameAndPassword(Login login) {
        User result = userRepo.getByUserNameAndPassword(login.getUserName(),login.getPassword());
        result.setPassword(result.getPassword().replaceAll(".","*"));;
        return result;
    }

    @Override
    public boolean delete(Long id) {
        boolean found = userRepo.existsById(id);
        if(found) userRepo.deleteById(id);
        return found;
    }
}
