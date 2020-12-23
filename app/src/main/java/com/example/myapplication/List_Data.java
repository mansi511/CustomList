package com.example.myapplication;

public class List_Data {
    private String repoName;
    private String ownerName;
    private String url_owner;

    public List_Data(String repoName,String ownerName,String url_owner) {
        this.repoName = repoName;
        this.ownerName= ownerName;
        this.url_owner = url_owner;
    }

    public String getNameString() {
        return repoName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getOwnerUrl() { return url_owner; }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
}
