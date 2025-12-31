package com.journal_app.backend_journal_app.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

public class Entries {


    private long id;
    private java.sql.Timestamp created_at;
    private String title;
    private java.sql.Date entry_date;
    private String what_did;
    private String what_learned;
    private java.sql.Timestamp updated_at;
    private UUID user_id;

    public Entries(){

    }

    public Entries(long id, java.sql.Timestamp created_at, String title,
                   java.sql.Date entry_date, String what_did, String what_learned,
                   java.sql.Timestamp updated_at, UUID user_id){
        this.id = id;
        this.created_at = created_at;
        this.title = title;
        this.entry_date = entry_date;
        this.what_did = what_did;
        this.what_learned = what_learned;
        this.updated_at = updated_at;
        this.user_id = user_id;

    }

    //getters
    public long getId() {
        return id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public String getTitle() {
        return title;
    }

    public Date getEntry_date() {
        return entry_date;
    }

    public String getWhat_did() {
        return what_did;
    }

    public String getWhat_learned() {
        return what_learned;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public UUID getUser_id() {
        return user_id;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setEntry_date(Date entry_date) {
        this.entry_date = entry_date;
    }

    public void setWhat_did(String what_did) {
        this.what_did = what_did;
    }

    public void setWhat_learned(String what_learned) {
        this.what_learned = what_learned;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entries entries = (Entries) o;
        return id == entries.id && Objects.equals(created_at, entries.created_at) && Objects.equals(title, entries.title) && Objects.equals(entry_date, entries.entry_date) && Objects.equals(what_did, entries.what_did) && Objects.equals(what_learned, entries.what_learned) && Objects.equals(updated_at, entries.updated_at) && Objects.equals(user_id, entries.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, created_at, title, entry_date, what_did, what_learned, updated_at, user_id);
    }
}
