package com.meetupbackend.entity.jwtBlacklist;

import jakarta.persistence.*;

@Entity
@Table(name="jwtblacklist")
public class JwtBlacklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="jwt")
    String jwt;

    public JwtBlacklist() {
    }


    public JwtBlacklist(String jwt) {
        this.jwt = jwt;
    }

    public int getId() {
        return id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
