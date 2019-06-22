package com.pulgupta.jwt.Service2.model;

public class User {
    String id;
    String name;
    String companyName;
    String city;

    public User(String id, String name, String companyName, String city) {
        this.id = id;
        this.name = name;
        this.companyName = companyName;
        this.city = city;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCity() {
        return city;
    }
}

