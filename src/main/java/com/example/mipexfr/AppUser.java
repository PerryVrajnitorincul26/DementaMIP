package com.example.mipexfr;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "app_user")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username, password;
    private boolean isAdmin;

    protected AppUser(){}
    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    @OneToMany(mappedBy = "app_user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AppTable> tables ;

    @Override
    public String toString() {
        if(this == null){
            return "NULL";
        }
        return String.format("User[id=%d, username=%s, passwd=%s]", id, username, password);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPassword() {
        return password;
    }

    public List<AppTable> getTables() {
        return tables;
    }

    public void setTables(AppTable table) {
        this.tables.add(table);
    }
}
