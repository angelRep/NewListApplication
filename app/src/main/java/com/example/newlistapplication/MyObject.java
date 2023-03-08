package com.example.newlistapplication;

public class MyObject {
    private String fullname;
    private String description;
    private String email;
    private String phone;

    public MyObject(String fullname, String description, String email, String phone) {
        this.fullname = fullname;
        this.description = description;
        this.email = email;
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }
    public String getDescription() {
        return description;
    }
    public String getEmail() {
        return email;
    }
    public String getPhone() {
        return phone;
    }
}
