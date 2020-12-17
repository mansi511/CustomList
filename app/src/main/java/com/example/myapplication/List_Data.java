package com.example.myapplication;

public class List_Data {
    private String repoName;
    private String ownerName;

    public List_Data(String repoName,String ownerName) {
        this.repoName = repoName;
        this.ownerName= ownerName;
    }

    public String getNameString() {
        return repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
