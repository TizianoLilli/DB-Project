package org.eschool.model;

import org.eschool.utils.Ruolo;

public class Account {
    private int id;
    private String username;
    private String password;
    private Ruolo role;

    public Account(int id, String user, String pass, Ruolo role){
        this.id = id;
        this.username = user;
        this.password = pass;
        this.role = role;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsername(){return this.username;}

    public void setUsername(String user) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public Ruolo getRole() {return role;}

    public void setRole(Ruolo role) {this.role = role;}
}
