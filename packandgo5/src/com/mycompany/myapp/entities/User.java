/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author raslen
 */
public class User {

    public static Object get(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int id;
    private String email, password, FirstName, LastName;
    private Date birthday;
    private int Number;
    private Roles role;

    public User(int id, String email, String password, String FirstName, String LastName, Date birthday, int Number) {

        this.id = id;
        this.email = email;
        this.password = password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.birthday = birthday;
        this.Number = Number;

    }

    public User(int id, String email, String password, String FirstName, String LastName, Date birthday, int Number, Roles role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.birthday = birthday;
        this.Number = Number;
        this.role = role;
    }

    public User(String email, String password, String FirstName, String LastName, Date birthday, int Number) {
        this.email = email;
        this.password = password;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.birthday = birthday;
        this.Number = Number;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(String FirstName, String email) {
        this.FirstName = FirstName;
        this.email = email;

    }

    // getters - setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public Date getbirthday() {
        return birthday;
    }

    public void setbirthday(Date birthday) {
        this.birthday = birthday;

    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int Number) {
        this.Number = Number;

    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + ", password=" + password + ", FirstName=" + FirstName + ", LastName=" + LastName + ", birthday=" + birthday + ", Number=" + Number + ", role=" + role + '}';
    }

}
