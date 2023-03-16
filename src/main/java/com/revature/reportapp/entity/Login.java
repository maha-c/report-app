package com.revature.reportapp.entity;
import lombok.*;
import javax.persistence.*;

@NoArgsConstructor


@Entity
public class Login {
    @Id
    private String userName;

    private String password;

    public Login(String userName,String password) {
        this.userName = userName;
        this.password = password;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
