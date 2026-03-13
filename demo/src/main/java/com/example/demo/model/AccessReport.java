package com.example.demo.model;

import java.util.List;

public class AccessReport {

    private String user;
    private List<String> repositories;

    public AccessReport() {
    }

    public AccessReport(String user, List<String> repositories) {
        this.user = user;
        this.repositories = repositories;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public List<String> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<String> repositories) {
        this.repositories = repositories;
    }
}