package com.revature.reportapp.repository;

import com.revature.reportapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {

    public User getByUserNameAndPassword(String userName,String password);

    List<User> getByUserName(String userName);


}
