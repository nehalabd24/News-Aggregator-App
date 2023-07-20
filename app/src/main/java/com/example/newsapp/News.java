package com.example.newsapp;

public class News {
    int id;
    String url;
    String heading;
    String description;
    String reference;

    public News(int id, String url, String heading, String description, String reference) {
        this.id = id;
        this.url = url;
        this.heading = heading;
        this.description = description;
        this.reference = reference;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}