package ru.teamidea.servicelayer.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Timofey Klyubin on 26.10.17
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = -7986723316192400687L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
