package com.example.notebook.model;

public class Note {
    private long id;
    private String headline;
    private String body;

    public Note(long id, String headline, String body) {
        this.id=id;
        this.headline = headline;
        this.body = body;
    }
    public Note( String nHeadline, String nBody) {
        this.headline = nHeadline;
        this.body = nBody;
    }

    public Note() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String nHeadline) {
        this.headline = nHeadline;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String nBody) {
        this.body = body;
    }





}
