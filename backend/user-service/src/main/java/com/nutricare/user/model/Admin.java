package com.nutricare.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin {
    @Id
    @Column(name = "admin_id", nullable = false)
    private Integer adminId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @OneToOne
    @MapsId
    @JoinColumn(name = "admin_id", referencedColumnName = "account_id")
    private Account account;

    public Admin() {
    }

    public Admin(Integer adminId, String username, String hashPassword) {
        this.adminId = adminId;
        this.username = username;
        this.hashPassword = hashPassword;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
