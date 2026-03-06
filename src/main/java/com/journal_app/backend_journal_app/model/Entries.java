package com.journal_app.backend_journal_app.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;
@Getter
@Setter
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

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @Column(name = "users")
    private UUID users;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false, nullable = false)
    private Instant createdAt;


    public Entries(){

    }

    public Entries(long id, String title,
                    String what_did, String what_learned,
                    UUID users, LocalDate entryDate){
        this.id = id;
        this.title = title;
        this.what_did = what_did;
        this.what_learned = what_learned;
        this.entryDate = entryDate;
        this.users = users;

    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Entries entries = (Entries) o;
        return id == entries.id && Objects.equals(title, entries.title) && Objects.equals(what_did, entries.what_did) && Objects.equals(what_learned, entries.what_learned) && Objects.equals(users, entries.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, what_did, what_learned, users);
    }
}
