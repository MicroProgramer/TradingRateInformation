package com.tradingrateinformation.model;

public class USER {
    String id,name,email,password,category;

    public USER() {
    }

    public USER(String id, String name, String email, String password, String category) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
