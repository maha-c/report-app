package com.revature.reportapp.service;
import com.revature.reportapp.entity.User;
import com.revature.reportapp.dto.Login;
import com.revature.reportapp.repository.UserRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @MockBean(UserRepo.class)
    private UserRepo userRepo;
    @Autowired
    UserServiceImpl userService;

    @Test
    public void testInsert(){
        User user1 = new User(3l,"Elsa","elsa123","COUNCIL");
        User user2 = new User(5l,"Anna","anna12","CONSTITUENT");
        Mockito.when(userRepo.save(user1)).thenReturn(user2);
        Assertions.assertEquals(user2,userService.insert(user1));
    }

    @Test
    public void testGetById(){
        User user1 = new User(3l,"Patrick","pass123","COUNCIL");
        Mockito.when(userRepo.findById(3l)).thenReturn(Optional.of(user1));
        Assertions.assertEquals(user1,userService.getById(3l));
    }

    @Test
    public void testGetAll(){
        List<User> users = new ArrayList<>(){{
            User user1 = new User(1l,"Elsa","elsa123","COUNCIL");
            User user2 = new User(2l,"Anna","anna456","CONSTITUENT");
            User user3 = new User(3l,"Alex","alexarrow23","CONSTITUENT");
        }};
        Mockito.when(userRepo.findAll()).thenReturn(users);
        Assertions.assertEquals(users,userService.getAll());
    }

    @Test
    public void testUpdate(){
        User user1 = new User(1l,"Elsa","elsa123","COUNCIL");
        User user2 = new User(1l,"Elsa","elsa789","COUNCIL");
        Mockito.when(userRepo.save(user1)).thenReturn(user2);
        Assertions.assertEquals(user2,userService.update(user1));
    }

    @Test
    public void testDelete(){
        Mockito.when(userRepo.existsById((long)3)).thenReturn(true);
        Mockito.when(userRepo.existsById((long)4)).thenReturn(false);
        Assertions.assertTrue(userService.delete((long)3));
        Assertions.assertFalse(userService.delete((long)4));
    }

    @Test
    public void testGetUserNameAndPassword(){
        User user1 = new User(8l,"elsa","elsa123","COUNCIL");
        Login log = new Login("elsa","elsa123");
        Mockito.when(userRepo.getByUserNameAndPassword("elsa","elsa123")).thenReturn(user1);
        User user2 = userService.getByUserNameAndPassword(log);
        Assertions.assertEquals("elsa",user2.getUserName());
        Assertions.assertEquals("*******",user2.getPassword());
    }


    }



