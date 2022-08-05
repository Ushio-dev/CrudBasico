package com.inventario.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "token")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    @OneToOne(fetch = FetchType.LAZY)
    private AppUser user;
    @Column(name = "expire_data")
    private Instant expireDate;

    public VerificationToken() {
    }

    public VerificationToken(Long id, String token, AppUser user, Instant expireDate) {
        this.id = id;
        this.token = token;
        this.user = user;
        this.expireDate = expireDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Instant getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Instant expireDate) {
        this.expireDate = expireDate;
    }
}
