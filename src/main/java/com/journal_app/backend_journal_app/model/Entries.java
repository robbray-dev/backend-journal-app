package com.journal_app.backend_journal_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;
@Table(name  = "entries")
@Entity
public class Entries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;


    @Column(name = "what_did")
    private String what_did;

    @Column(name = "what_learned")
    private String what_learned;

    @Column(name = "users")
    private UUID user_id;

    public Entries(){

    }

    public Entries(long id, String title,
                   java.sql.Date entry_date, String what_did, String what_learned,
                    UUID user_id){
        this.id = id;
        this.title = title;
        this.what_did = what_did;
        this.what_learned = what_learned;
        this.user_id = user_id;

    }

    //getters
    public long getId() {
        return id;
    }


    public String getTitle() {
        return title;
    }


    public String getWhat_did() {
        return what_did;
    }

    public String getWhat_learned() {
        return what_learned;
    }


    public UUID getUser_id() {
        return user_id;
    }

    //setters
    public void setId(long id) {
        this.id = id;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public void setWhat_did(String what_did) {
        this.what_did = what_did;
    }

    public void setWhat_learned(String what_learned) {
        this.what_learned = what_learned;
    }


    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entries entries = (Entries) o;
        return id == entries.id && Objects.equals(title, entries.title) && Objects.equals(what_did, entries.what_did) && Objects.equals(what_learned, entries.what_learned) && Objects.equals(user_id, entries.user_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, what_did, what_learned, user_id);
    }
}
